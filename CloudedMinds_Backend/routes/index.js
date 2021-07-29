var express = require('express');
var router = express.Router();
var bodyParser = require("body-parser");
const entry = require('../controllers/entries');
const patient = require('../controllers/patients');

/* GET home page. */
router.get('/', function(req, res, next) {
  entry.listData(req,res);
});

router.post('/', function (req, res){

  entry.insert(req, res);
})

/* GET users page. */
router.get('/patients', function(req, res, next) {
  patient.listPatients(req,res);
});

router.post('/patients', function (req, res){

  patient.insertPatient(req, res);
})

router.post('/login', function (req, res){

  patient.loginPatient(req, res);
})

module.exports = router;
