let mongoose = require('mongoose');
let Schema = mongoose.Schema;

let Patient = new Schema(
    {
        name: {type: String},
        username: {type: String, unique: true},
        password: {type: String}
    }
);

let patientModel = mongoose.model('Patient', Patient);

module.exports = patientModel;

