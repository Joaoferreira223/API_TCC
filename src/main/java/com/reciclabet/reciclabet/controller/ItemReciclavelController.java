package com.reciclabet.reciclabet.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.reciclabet.reciclabet.Config.ResourceNotFoundException;
import com.reciclabet.reciclabet.model.ItensReciclaveis;
import com.reciclabet.reciclabet.service.ItensReciclaveisService;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/itens_reciclaveis")
public class ItemReciclavelController {

    @Autowired
    private ItensReciclaveisService itemReciclavelService;

    // Listar todos os itens recicláveis
    @GetMapping
    public ResponseEntity<List<ItensReciclaveis>> listarItensReciclaveis() {
        List<ItensReciclaveis> itens = itemReciclavelService.listarItensReciclaveis();
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    // Criar um novo item reciclável
    @PostMapping
    public ResponseEntity<ItensReciclaveis> criarItemReciclavel(@RequestBody ItensReciclaveis itensReciclaveis) {
        ItensReciclaveis itemCriado = itemReciclavelService.criarItemReciclavel(itensReciclaveis);
        return new ResponseEntity<>(itemCriado, HttpStatus.CREATED);
    }

    // Buscar um item reciclável por ID
    @GetMapping("/{id}")
    public ResponseEntity<ItensReciclaveis> buscarItemReciclavelPorId(@PathVariable Long id) {
        ItensReciclaveis item = itemReciclavelService.buscarItemReciclavelPorId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    // Atualizar informações de um item reciclável
    @PutMapping("/{id}")
    public ResponseEntity<ItensReciclaveis> atualizarItemReciclavel(@PathVariable Long id, @RequestBody ItensReciclaveis itemAtualizado) {
        try {
            ItensReciclaveis itemReciclavel = itemReciclavelService.atualizarItemReciclavel(id, itemAtualizado);
            return new ResponseEntity<>(itemReciclavel, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Deletar um item reciclável
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarItemReciclavel(@PathVariable Long id) {
        itemReciclavelService.deletarItemReciclavel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


            @GetMapping("/exportar")
            public ResponseEntity<byte[]> exportarExcel() {
                List<ItensReciclaveis> itens = itemReciclavelService.listarItensReciclaveis();


                try (Workbook workbook = new XSSFWorkbook()) {
        Sheet sheet = workbook.createSheet("Itens Recicláveis");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nome");
        header.createCell(1).setCellValue("Material");
        header.createCell(2).setCellValue("Valor Kilo");
        header.createCell(3).setCellValue("Descrição");

        int rowNum = 1;
        for (ItensReciclaveis item : itens) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getNome());
            row.createCell(1).setCellValue(item.getMaterial());
            row.createCell(2).setCellValue(item.getValorKilo());
           row.createCell(3).setCellValue(item.getDescricao() != null ? item.getDescricao() : "");

        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=itens_reciclaveis.xlsx")
                .body(out.toByteArray());
    } catch (Exception e) {
        return ResponseEntity.internalServerError().build();
    }
}


@PostMapping("/importar")
public ResponseEntity<String> importarExcel(@RequestParam("file") MultipartFile file) {
    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            ItensReciclaveis item = new ItensReciclaveis();
            item.setNome(row.getCell(0).getStringCellValue());
            item.setMaterial(row.getCell(1).getStringCellValue());
            item.setValorKilo(row.getCell(2).getNumericCellValue());
            item.setDescricao(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : null);

            ItensReciclaveisService.salvar(item);
        }
        return ResponseEntity.ok("Importação concluída com sucesso!");
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Erro ao importar planilha: " + e.getMessage());
    }
}



}
