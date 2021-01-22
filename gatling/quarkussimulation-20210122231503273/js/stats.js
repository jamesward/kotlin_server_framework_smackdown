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
        "total": "1",
        "ok": "1",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3474",
        "ok": "3474",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "690",
        "ok": "690",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "486",
        "ok": "486",
        "ko": "-"
    },
    "percentiles1": {
        "total": "592",
        "ok": "592",
        "ko": "-"
    },
    "percentiles2": {
        "total": "997",
        "ok": "998",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1585",
        "ok": "1585",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2564",
        "ok": "2564",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 7045,
    "percentage": 64
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 2911,
    "percentage": 26
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1044,
    "percentage": 9
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
        "total": "19",
        "ok": "19",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3319",
        "ok": "3319",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "619",
        "ok": "619",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "381",
        "ok": "381",
        "ko": "-"
    },
    "percentiles1": {
        "total": "512",
        "ok": "512",
        "ko": "-"
    },
    "percentiles2": {
        "total": "928",
        "ok": "928",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1173",
        "ok": "1173",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1757",
        "ok": "1757",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 6825,
    "percentage": 68
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 2732,
    "percentage": 27
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 443,
    "percentage": 4
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
        "total": "1",
        "ok": "1",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3474",
        "ok": "3474",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1391",
        "ok": "1391",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "783",
        "ok": "783",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1407",
        "ok": "1407",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1883",
        "ok": "1883",
        "ko": "-"
    },
    "percentiles3": {
        "total": "2758",
        "ok": "2758",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3252",
        "ok": "3252",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 220,
    "percentage": 22
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 179,
    "percentage": 18
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 601,
    "percentage": 60
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
