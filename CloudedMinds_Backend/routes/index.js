var express = require('express');
var router = express.Router();
var bodyParser = require("body-parser");
const entry = require('../controllers/entries');

/* GET home page. */
router.get('/', function(req, res, next) {
  entry.listData(req,res);
});

router.post('/', function (req, res){

  entry.insert(req, res);
})

module.exports = router;
