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
        "total": "8110",
        "ok": "8110",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "656",
        "ok": "656",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "627",
        "ok": "627",
        "ko": "-"
    },
    "percentiles1": {
        "total": "535",
        "ok": "535",
        "ko": "-"
    },
    "percentiles2": {
        "total": "722",
        "ok": "722",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1853",
        "ok": "1853",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3714",
        "ok": "3714",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 10124,
    "percentage": 92
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 876,
    "percentage": 8
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1222.222",
        "ok": "1222.222",
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
        "total": "2",
        "ok": "2",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "8110",
        "ok": "8110",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "681",
        "ok": "681",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "650",
        "ok": "650",
        "ko": "-"
    },
    "percentiles1": {
        "total": "560",
        "ok": "560",
        "ko": "-"
    },
    "percentiles2": {
        "total": "724",
        "ok": "724",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1906",
        "ok": "1906",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3725",
        "ok": "3725",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 9124,
    "percentage": 91
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 876,
    "percentage": 9
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1111.111",
        "ok": "1111.111",
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
        "total": "768",
        "ok": "768",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "401",
        "ok": "401",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "180",
        "ok": "180",
        "ko": "-"
    },
    "percentiles1": {
        "total": "420",
        "ok": "420",
        "ko": "-"
    },
    "percentiles2": {
        "total": "481",
        "ok": "481",
        "ko": "-"
    },
    "percentiles3": {
        "total": "728",
        "ok": "728",
        "ko": "-"
    },
    "percentiles4": {
        "total": "755",
        "ok": "755",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 1000,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "111.111",
        "ok": "111.111",
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
