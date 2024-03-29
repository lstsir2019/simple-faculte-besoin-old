/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebesoin.domain.rest;

import com.faculte.simplefacultebesoin.commun.util.DateUtil;
import com.faculte.simplefacultebesoin.domain.bean.ExpressionBesoinItem;
import com.faculte.simplefacultebesoin.domain.model.service.ExpressionBesoinItemService;
import com.faculte.simplefacultebesoin.domain.rest.converter.AbstractConverter;
import com.faculte.simplefacultebesoin.domain.rest.vo.ExpressionBesoinItemVo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ismail boulaanait
 */
@RestController()
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping({"/faculte-besoin/item"})
public class ExpressionBesoinItemRest {

    @Autowired
    ExpressionBesoinItemService expressionBesoinItemService;

    @Autowired
    @Qualifier("expressionBesoinItemConverter")
    private AbstractConverter<ExpressionBesoinItem, ExpressionBesoinItemVo> expressionBesoinItemConverter;

    //======================================================================================
    //@PutMapping("/delete/{id}")
    @DeleteMapping("/delete/{id}")
    public int deleteItem(@PathVariable Long id) {
        return expressionBesoinItemService.deleteItem(id);
    }

    @PutMapping("/accorder")
    public int accoder(@RequestBody ExpressionBesoinItemVo expressionBesoinItem) {
        ExpressionBesoinItem ebi = expressionBesoinItemConverter.toItem(expressionBesoinItem);
        return expressionBesoinItemService.accoder(ebi);
    }

    @GetMapping("/find/{id}")
    public ExpressionBesoinItemVo findById(@PathVariable Long id) {
        ExpressionBesoinItemVo ebiv = expressionBesoinItemConverter.toVo(expressionBesoinItemService.findById(id));
        String entite = expressionBesoinItemService.findById(id).getExpressionBesoin().getCodeEntity();
        ebiv.setEntityAdmin(entite);
        return ebiv;
    }

    @GetMapping("/produit/{referenceProduit}")
    public List<ExpressionBesoinItemVo> findByReferenceProduit(@PathVariable String referenceProduit) {
        return expressionBesoinItemService.findByReferenceProduit(referenceProduit);
    }

    @GetMapping("/entity/{codeEntity}")
    public List<ExpressionBesoinItemVo> findByExpressionBesoinCodeEntity(@PathVariable String codeEntity) {
        return expressionBesoinItemConverter.toVo(expressionBesoinItemService.findByExpressionBesoinCodeEntity(codeEntity));
    }

    @PutMapping("/reference/{id}/qteLivre/{qte}/increment")
    public int incrementQteLivre(@PathVariable Long id, @PathVariable int qte) {
        return expressionBesoinItemService.incrementQteLivre(id, qte);
    }
    @PutMapping("/reference/{id}/qteLivre/{qte}/decrement")
    public int decrementQteLivre(@PathVariable Long id, @PathVariable int qte) {
        return expressionBesoinItemService.decrementQteLivre(id, qte);
    }

    @PutMapping("/reference/{id}/qteCommande/{qte}/increment")
    public int incrementQteCommande(@PathVariable Long id,@PathVariable int qte) {
        return expressionBesoinItemService.incrementQteCommande(id, qte);
    }
    
    @PutMapping("/reference/{id}/qteCommande/{qte}/decrement")
    public int decrementQteCommande(@PathVariable Long id,@PathVariable int qte) {
        return expressionBesoinItemService.decrementQteCommande(id, qte);
    }

    
    @PostMapping("/search/referenceProduit/{referenceProduit}")
    public List<ExpressionBesoinItemVo> searchByDate(@RequestBody ExpressionBesoinItemVo ebiv,@PathVariable String referenceProduit) {
        Date dateMin=DateUtil.parseDate(ebiv.getDateMin());
        Date dateMax=DateUtil.parseDate(ebiv.getDateMax());
        return expressionBesoinItemService.searchByDate(dateMin,dateMax, referenceProduit);
    }
    

    // ===========Getter & Setter ===========
    public ExpressionBesoinItemService getExpressionBesoinItemService() {
        return expressionBesoinItemService;
    }

    public void setExpressionBesoinItemService(ExpressionBesoinItemService expressionBesoinItemService) {
        this.expressionBesoinItemService = expressionBesoinItemService;
    }

    public AbstractConverter<ExpressionBesoinItem, ExpressionBesoinItemVo> getExpressionBesoinItemConverter() {
        return expressionBesoinItemConverter;
    }

    public void setExpressionBesoinItemConverter(AbstractConverter<ExpressionBesoinItem, ExpressionBesoinItemVo> expressionBesoinItemConverter) {
        this.expressionBesoinItemConverter = expressionBesoinItemConverter;
    }

}
