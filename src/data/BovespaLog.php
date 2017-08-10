<?php
namespace src\data;
/**
 * Description of BovespaLog
 *
 * @author Perassoli
 */

class BovespaLog {
    
    public function __construct($log) {
        $this->setLogCode($log);
        $this->setLogDate($log);
        $this->setBDICode($log);
        $this->setTradeCode($log);
        $this->setMarketCode($log);
        $this->setCompanyShortName($log);
        $this->setShareTypeCode($log);
        $this->setMarketTermInDays($log);
        $this->setCurrency($log);
        $this->setOpeningPrice($log);
        $this->setMaxPrice($log);
        $this->setMinPrice($log);
        $this->setMeanPrice($log);
        $this->setLastPrice($log);
        $this->setBestBuyPrice($log);
        $this->setBestSalePrice($log);
        $this->setQttOfTrades($log);
        $this->setQttOfTradedShares($log);
        $this->setVolumeOfTradedShares($log);
        $this->setFactor($log);
    }
    
    public $logCode;
    public function setLogCode($log){
        $this->logCode = substr($log, 0, 2);
    }
    
    public $logDate;
    public function setLogDate($log){
        $this->logDate = substr($log, 2, 8);
    }
    
    public $bdiCode;
    public function setBDICode($log){
        $this->bdiCode = substr($log, 10, 2);
    }
    
    public $tradeCode;
    public function setTradeCode($log){
        $this->tradeCode = substr($log, 12, 12);
    }
    
    public $marketCode;
    public function setMarketCode($log){
        $this->marketCode = substr($log, 24, 3);
    }
    
    public $companyShortName;
    public function setCompanyShortName($log){
        $this->companyShortName = substr($log, 27, 12);
    }
    
    public $shareTypeCode;
    public function setShareTypeCode($log){
        $this->shareTypeCode = substr($log, 39, 10);
    }
    
    public $marketTermInDays;
    public function setMarketTermInDays($log){
        $this->marketTermInDays = substr($log, 49, 3);
    }
    
    public $currency;
    public function setCurrency($log){
        $this->currency = substr($log, 52, 4);
    }
    
    
    public $openingPrice;
    public function setOpeningPrice($log){
        $this->openingPrice = substr($log, 56, 11);
    }
    
    public $maxPrice;
    public function setMaxPrice($log){
        $this->maxPrice = substr($log, 69, 11);
    }
    
    public $minPrice;
    public function setMinPrice($log){
        $this->minPrice = substr($log, 82, 11);
    }
    
    public $meanPrice;
    public function setMeanPrice($log){
        $this->meanPrice = substr($log, 95, 11);
    }
    
    public $lastPrice;
    public function setLastPrice($log){
        $this->lastPrice = substr($log, 108, 11);
    }
    
    public $bestBuyPrice;
    public function setBestBuyPrice($log){
        $this->bestBuyPrice = substr($log, 121, 11);
    }
    
    public $bestSalePrice;
    public function setBestSalePrice($log){
        $this->bestSalePrice = substr($log, 134, 11);
    }
    
    public $qttOfTrades;
    public function setQttOfTrades($log){
        $this->qttOfTrades = substr($log, 147, 5);
    }
    
    
    public $qttOfTradedShares;
    public function setQttOfTradedShares($log){
        $this->qttOfTradedShares = substr($log, 152, 18);
    }
    
    public $volumeOfTradedShares;
    public function setVolumeOfTradedShares($log){
        $this->volumeOfTradedShares = substr($log, 170, 16);
    }
    
    public $factor;
    public function setFactor($log){
        $this->factor = substr($log, 210, 7);
    }
    
}

?>
