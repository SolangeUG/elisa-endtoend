zhain.prototype.waitUntil = function(predicate) {
    return this.do(function(done) {
        waitUntil(predicate, function() {
            done();
        });
    });
}

var UI_TEST_TIMEOUT_MILLIS = 10000;

function waitUntil(condition, done, timestamp) {
    if (condition()) {
        done();
        return;
    }
    checkCondition(condition, done, timestamp);
    function checkCondition(condition, done, timestamp) {
        if (timestamp === undefined) {
            timestamp = new Date().getTime();
        }

        var timedOut = (new Date().getTime() > timestamp + UI_TEST_TIMEOUT_MILLIS);
        if (!timedOut) {
            if (condition()) {
                done();
            } else {
                setTimeout(function () {
                    checkCondition(condition, done, timestamp)
                }, 6)
            }
        } else {
            console.log("WAIT TERMINATED");
            console.log(condition);
            chai.assert(false, 'Did not satisfy condition in '+ UI_TEST_TIMEOUT_MILLIS + ' milliseconds: ' + condition)
        }
    }
}