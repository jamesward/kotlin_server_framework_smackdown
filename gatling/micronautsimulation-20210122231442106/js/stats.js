var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "11000",
        "ok": "11000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "109",
        "ok": "109",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2185",
        "ok": "2185",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "821",
        "ok": "821",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "314",
        "ok": "314",
        "ko": "-"
    },
    "percentiles1": {
        "total": "730",
        "ok": "730",
        "ko": "-"
    },
    "percentiles2": {
        "total": "894",
        "ok": "894",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1530",
        "ok": "1530",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1955",
        "ok": "1955",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 6837,
    "percentage": 62
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 2859,
    "percentage": 26
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1304,
    "percentage": 12
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1100",
        "ok": "1100",
        "ko": "-"
    }
},
contents: {
"req_get-b5eda": {
        type: "REQUEST",
        name: "get",
path: "get",
pathFormatted: "req_get-b5eda",
stats: {
    "name": "get",
    "numberOfRequests": {
        "total": "10000",
        "ok": "10000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "109",
        "ok": "109",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2185",
        "ok": "2185",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "821",
        "ok": "821",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "322",
        "ok": "322",
        "ko": "-"
    },
    "percentiles1": {
        "total": "717",
        "ok": "717",
        "ko": "-"
    },
    "percentiles2": {
        "total": "893",
        "ok": "892",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1620",
        "ok": "1620",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1959",
        "ok": "1959",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 6379,
    "percentage": 64
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 2400,
    "percentage": 24
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1221,
    "percentage": 12
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1000",
        "ok": "1000",
        "ko": "-"
    }
}
    },"req_add-34ec7": {
        type: "REQUEST",
        name: "add",
path: "add",
pathFormatted: "req_add-34ec7",
stats: {
    "name": "add",
    "numberOfRequests": {
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "275",
        "ok": "275",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1366",
        "ok": "1366",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "821",
        "ok": "821",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "217",
        "ok": "217",
        "ko": "-"
    },
    "percentiles1": {
        "total": "816",
        "ok": "816",
        "ko": "-"
    },
    "percentiles2": {
        "total": "925",
        "ok": "925",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1214",
        "ok": "1214",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1302",
        "ok": "1302",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 458,
    "percentage": 46
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 459,
    "percentage": 46
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 83,
    "percentage": 8
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "100",
        "ok": "100",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
