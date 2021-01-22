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
        "total": "168",
        "ok": "168",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2178",
        "ok": "2178",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "710",
        "ok": "710",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "344",
        "ok": "344",
        "ko": "-"
    },
    "percentiles1": {
        "total": "552",
        "ok": "552",
        "ko": "-"
    },
    "percentiles2": {
        "total": "888",
        "ok": "888",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1380",
        "ok": "1379",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1911",
        "ok": "1910",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 7693,
    "percentage": 70
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 2259,
    "percentage": 21
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1048,
    "percentage": 10
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
        "total": "168",
        "ok": "168",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2178",
        "ok": "2178",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "679",
        "ok": "679",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "341",
        "ok": "341",
        "ko": "-"
    },
    "percentiles1": {
        "total": "531",
        "ok": "531",
        "ko": "-"
    },
    "percentiles2": {
        "total": "788",
        "ok": "784",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1444",
        "ok": "1444",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1918",
        "ok": "1918",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 7537,
    "percentage": 75
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 1527,
    "percentage": 15
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 936,
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
        "total": "358",
        "ok": "358",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1320",
        "ok": "1320",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "1011",
        "ok": "1011",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "198",
        "ok": "198",
        "ko": "-"
    },
    "percentiles1": {
        "total": "1061",
        "ok": "1061",
        "ko": "-"
    },
    "percentiles2": {
        "total": "1171",
        "ok": "1171",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1266",
        "ok": "1266",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1316",
        "ok": "1316",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 156,
    "percentage": 16
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 732,
    "percentage": 73
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 112,
    "percentage": 11
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
