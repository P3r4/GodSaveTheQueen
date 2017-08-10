<?php
namespace src\data;

/**
 * Description of BovespaModel
 *
 * @author Perassoli
 */
class BovespaDigest {

    public $filename;
    public $header;
    public $trailer;
    public $bovespaLogList;
    public $size;
    
    public function __construct($filename) {
        $this->filename = $filename;
        $data = file_get_contents($filename);
        $logList = explode(PHP_EOL, $data);
        $size = count($logList);
        $this->header = $logList[0];
        $this->trailer = $logList[$size-2];
        unset($logList[0]);
        unset($logList[$size-1]);
        unset($logList[$size-2]);
        $this->size = $size-3;
        $this->bovespaLogList = array();
        foreach ($logList as $log) {
            array_push($this->bovespaLogList, new BovespaLog($log));
        }
    }
    
    
}

?>
