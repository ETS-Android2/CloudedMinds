//Require the dev-dependencies
let chai = require('chai');
let expect = chai.expect;
let chaiHttp = require('chai-http');
let should = chai.should();
chai.use(chaiHttp);

describe('Entries Testing', function () {

    // Testing displaying all entries
    it('GET: All Entries ', function (done) {
        chai.request( 'http://localhost:3000')
            .get('/')
            .end((err, res) => {
                res.should.have.status(200);
             done();
            })
    });

    // Testing adding new entry
    it('POST: New Entry ', function (done) {
        chai.request('http://localhost:3000')
            .post('/')
            .send({
                user: "bross",
                event: "Testing event",
                date: "7/14/2021",
                mood: "Sad",
                mood_rating: "5",
                catastrophise: "Yes",
                generalise: "No",
                ignoring: "Yes",
                self_critical: "No",
                mind_reading: "Yes",
                changed_mood: "Feel better",
                changed_rating: "2"
            })
            .end((err, res) => {
                res.should.have.status(200);
             done();
            })
    });

    // Testing entry made with a missing data
    it('POST: Entry with Missing event ', function (done) {
        chai.request('http://localhost:3000')
            .post('/')
            .send({
                user: "bross",
                date: "7/14/2021",
                mood: "Sad",
                mood_rating: "5",
                catastrophise: "Yes",
                generalise: "No",
                ignoring: "Yes",
                self_critical: "No",
                mind_reading: "Yes",
                changed_mood: "Feel better",
                changed_rating: "2"
            })
            .end((err, res) => {
                res.should.have.status(500);
                done();
            })
    });

    // Testing JSON entries display
    it('GET: JSON Entries ', function (done) {
        chai.request('http://localhost:3000')
            .get('/entries')
            .end((err, res) => {
                res.should.have.status(200);
                done();
            })
    });
});
