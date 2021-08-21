var express = require('express');
var router = express.Router();
var bodyParser = require("body-parser");
const entry = require('../controllers/entries');
const patient = require('../controllers/patients');

var entries = require('../models/entries');

/* GET home page. */
router.get('/', function(req, res, next) {
  entry.listData(req,res);
});

/* GET home page. */
router.get('/entries', function(req, res, next) {
  entry.listJsonData(req,res);
});

router.post('/', function (req, res){

  entry.insert(req, res);
})

router.get('/delete/:id', function (req, res){

  entry.delete(req, res);
})

router.get('/search', function (req, res){

  entry.search(req, res);
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
