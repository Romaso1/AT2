/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 18.120458817976193, "KoPercent": 81.87954118202381};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.18065548543411175, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.10134446439838858, 500, 1500, "Open Issue ID 1"], "isController": false}, {"data": [0.08989418503057955, 500, 1500, "Reload Issue to Verify Comment"], "isController": false}, {"data": [0.1930138516468133, 500, 1500, "Open Login Page"], "isController": false}, {"data": [0.17985445904804337, 500, 1500, "Open Manage Users"], "isController": false}, {"data": [0.11114886181624035, 500, 1500, "View Issues Page"], "isController": false}, {"data": [0.18171655570970865, 500, 1500, "Open Manage Projects"], "isController": false}, {"data": [0.18437915264352034, 500, 1500, "Login"], "isController": false}, {"data": [0.08042908455489758, 500, 1500, "Add Comment to Issue"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 1466028, 1200377, 81.87954118202381, 26.70414412275731, 1, 737, 4.0, 108.0, 123.0, 146.0, 407.1992338356658, 3997.07348201964, 27.17122939773717], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Open Issue ID 1", 20603, 18076, 87.7347959035092, 56.33140804737173, 2, 737, 4.0, 269.90000000000146, 482.9500000000007, 600.0, 5.722936327715394, 1540.7102656932495, 0.22104519957917546], "isController": false}, {"data": ["Reload Issue to Verify Comment", 20602, 18384, 89.23405494612173, 49.710804776235435, 1, 685, 4.0, 196.0, 451.9500000000007, 599.0, 5.7242279155163756, 1294.0897392790896, 0.2036697604018018], "isController": false}, {"data": ["Open Login Page", 346457, 279586, 80.69861483531866, 25.63237284857893, 1, 279, 4.0, 103.0, 108.95000000000073, 122.0, 96.2417982921558, 271.10734699144547, 5.466235796322635], "isController": false}, {"data": ["Open Manage Users", 134670, 110449, 82.01455409519566, 28.791817034231915, 1, 342, 4.0, 127.0, 137.0, 157.0, 37.40967384664617, 213.65056976607633, 2.022556741442957], "isController": false}, {"data": ["View Issues Page", 20603, 18313, 88.88511381837597, 24.07309615104615, 2, 289, 4.0, 133.0, 172.0, 206.0, 5.723435528080892, 27.613161060814175, 0.2139741664446612], "isController": false}, {"data": ["Open Manage Projects", 200378, 163966, 81.82834442902913, 27.02041142241191, 1, 358, 4.0, 127.0, 134.0, 149.0, 55.66196256627598, 242.99606780207847, 3.0565369521319843], "isController": false}, {"data": ["Login", 702113, 572658, 81.56208473564797, 25.420852483859445, 1, 351, 4.0, 104.0, 115.0, 132.0, 195.02905937512412, 394.191075569271, 15.67717358471482], "isController": false}, {"data": ["Add Comment to Issue", 20602, 18945, 91.95709154451025, 21.73531695951854, 1, 268, 4.0, 121.0, 153.0, 184.0, 5.725137806807028, 13.339960019717156, 0.3124551898615985], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["500/Internal Server Error", 19990, 1.6653101483950459, 1.3635483087635434], "isController": false}, {"data": ["403/Forbidden", 488, 0.04065389456812318, 0.03328722234500296], "isController": false}, {"data": ["Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 1179899, 98.29403595703683, 80.48270565091526], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 1466028, 1200377, "Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 1179899, "500/Internal Server Error", 19990, "403/Forbidden", 488, "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["Open Issue ID 1", 20603, 18076, "Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 17940, "500/Internal Server Error", 136, "", "", "", "", "", ""], "isController": false}, {"data": ["Reload Issue to Verify Comment", 20602, 18384, "Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 18149, "500/Internal Server Error", 235, "", "", "", "", "", ""], "isController": false}, {"data": ["Open Login Page", 346457, 279586, "Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 274749, "500/Internal Server Error", 4837, "", "", "", "", "", ""], "isController": false}, {"data": ["Open Manage Users", 134670, 110449, "Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 108692, "500/Internal Server Error", 1757, "", "", "", "", "", ""], "isController": false}, {"data": ["View Issues Page", 20603, 18313, "Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 18091, "500/Internal Server Error", 222, "", "", "", "", "", ""], "isController": false}, {"data": ["Open Manage Projects", 200378, 163966, "Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 161119, "500/Internal Server Error", 2847, "", "", "", "", "", ""], "isController": false}, {"data": ["Login", 702113, 572658, "Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 562998, "500/Internal Server Error", 9660, "", "", "", "", "", ""], "isController": false}, {"data": ["Add Comment to Issue", 20602, 18945, "Non HTTP response code: java.net.BindException/Non HTTP response message: Address already in use: connect", 18161, "403/Forbidden", 488, "500/Internal Server Error", 296, "", "", "", ""], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
