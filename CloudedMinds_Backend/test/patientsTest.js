//Require the dev-dependencies
let chai = require('chai');
let expect = chai.expect;
let chaiHttp = require('chai-http');
let should = chai.should();
chai.use(chaiHttp);

describe('Patients Testing', function () {

    // Displays all patients
    it('GET: All Patients ', function (done) {
        chai.request( 'http://localhost:3000')
            .get('/patients')
            .end((err, res) => {
                res.should.have.status(200);
                done();
            })
    });

    // Testing Registering New Patient
    it('POST: New Patient ', function (done) {
        chai.request( 'http://localhost:3000')
            .post('/patients')
            .send({
                name: "John Smith",
                username: "jsmith21",
                password: "Smithy123"
            })
            .end((err, res) => {
                res.should.have.status(200);
                done();
            })
    });

    // Testing creating an account with an existing username
    it('POST: Creating Account with Existing Patient Username ', function (done) {
        chai.request( 'http://localhost:3000')
            .post('/patients')
            .send({
                name: "John Smith",
                username: "bross",
                password: "Smithy123"
            })
            .end((err, res) => {
                res.body.should.equal('Username already exists');
                done();
            })
    });

    // Testing Patient Login
    it('POST: Patient Login', function (done) {
        chai.request('http://localhost:3000')
            .post('/login')
            .send({
                username: "jsmith21",
                password: "Smithy123"
            })
            .end((err, res) => {
                res.body.should.equal('Login Successful');
                done();
            })
    });

    // Testing login with incorrect password
    it('POST: Patient Login with Incorrect Password', function (done) {
        chai.request('http://localhost:3000')
            .post('/login')
            .send({
                username: "jsmith21",
                password: "wrongpass"
            })
            .end((err, res) => {
                res.body.should.equal('Password is incorrect');
                done();
            })
    });

    // Testing login with incorrect username
    it('POST: Patient Login with Non-existent Username', function (done) {
        chai.request('http://localhost:3000')
            .post('/login')
            .send({
                username: "wronguser",
                password: "Smithy123"
            })
            .end((err, res) => {
                res.body.should.equal('Username does not exist');
                done();
            })
    });
});
