
var hostnameArr = new Array()
var switchHost = new Array();
var url = "http://ump.jd.com/performanceDetail/queryJsonData.action?timestamp=Mon%20Aug%2020%202018%2016:06:40%20GMT+0800%20(%D6%D0%B9%FA%B1%EA%D7%BC%CA%B1%BC%E4)";
$.ajax({
    type: "POST",
    url: url,
    data: {
        "datas.xData": 1534750680000,
        "datas.accessKey": "orderbank.web.orderbank.serviceorder.createOrderDirectly",
        "datas.appId": 3889,
        "datas.timeStr": "2018-08-20 15:39:00",
        "datas.tpName": "tp99",
        "datas.dType": "oneMinute",
        "datas.type": "tp",
        "datas.yData": 23,
        "datas.queryType": "all"
    },
    dataType: "json",
    success: function (data, textStatus) {
        for (index in data) {
            var item = data[index];
            var hostname = item.hostName;
            hostnameArr.push(hostname);
        }

        for(index1 in hostnameArr) {
            console.log(hostnameArr[index1]+",")
        }
    }
});



var hostnameArr= ["10.189.11.94","10.189.11.93","10.189.11.92","10.189.11.91","10.189.11.90","10.189.11.105","10.186.9.204","10.186.8.149","10.186.51.217","10.186.32.166","10.186.228.77","10.186.216.214","10.186.216.179","10.186.205.46","10.186.204.14","10.186.11.156","10.185.201.119","10.185.201.116","10.185.201.115","10.185.201.114","10.185.201.113","10.185.201.112","10.185.201.111","10.185.201.110","10.185.201.109","10.185.201.108","10.185.201.107","10.185.201.106","10.185.201.105","10.185.201.104","10.185.201.103","10.185.201.102","10.185.142.144","10.185.142.106","10.185.141.91","10.185.141.89","10.184.84.85","10.184.84.84","10.184.84.83","10.184.84.82","10.184.84.81","10.184.84.80","10.184.84.79","10.184.84.78","10.184.84.77","10.184.84.76","10.184.84.75","10.184.84.74","10.181.56.207","10.181.56.206","10.181.56.205","10.181.56.204","10.181.56.203","10.181.56.202","10.181.56.201","10.181.56.200","10.181.56.199","10.181.56.198","10.181.56.197","10.181.56.196","10.181.56.195","10.181.56.194","10.181.56.193","10.181.56.192","10.181.56.191","10.181.56.190","10.181.56.189","10.181.56.188","10.181.56.187","10.181.56.186","10.181.56.185","10.181.56.184","10.181.56.183","10.181.56.182","10.181.56.181","10.181.56.180","10.181.56.179","10.181.56.178","10.181.56.177","10.181.56.176","10.181.56.175","10.181.56.174","10.181.56.173","10.181.56.172","10.181.56.171","10.181.56.170","10.181.56.169","10.181.56.168","10.181.56.167","10.181.56.166","10.181.56.165","10.181.2.83","10.181.2.145","10.181.2.138","10.181.2.136","10.181.2.135","10.181.2.134","10.181.2.133","10.181.2.132","10.181.2.131","10.181.2.130","10.181.2.129","10.181.155.75","10.181.1.58","10.181.0.145","10.181.0.144","10.181.0.143","10.181.0.142","11.19.212.138","11.20.183.122","11.19.196.189","11.20.204.19","11.21.12.32","11.20.204.22","11.20.204.16","11.20.183.124","11.20.183.123","11.20.204.20","11.20.204.24","11.19.196.187","11.19.10.111","11.19.196.188","11.20.183.125","11.20.204.17","11.20.204.23","11.19.212.139","11.19.212.137","11.20.204.25","11.19.74.138","11.20.204.21","11.19.196.186","11.20.204.18","11.20.87.135","11.19.10.110"];
//var hostnameArr = ["10.189.11.94"];
var switchArr = new Array();
for(index2 in hostnameArr) {
    var hostname = hostnameArr[index2];
    var switchUrl = "http://np.jd.com/index.php/Admin/BandwidthNew/get_table_content?search=" + hostname + "&time=2018-08-20+16%3A52%3A49&hours=1&no_search_domain=0&no_search_vip=0";
    $.ajax({
        type: "GET",
        url: switchUrl,
        async:false,
        success: function (data, textStatus) {
            console.log("switchUrl::" + switchUrl);
            console.log("data::" + data);
            var html = data.html;
            console.log("html::" + html);
            var startIndex = html.indexOf("dev_ip=")+7;
            var endIndex = html.indexOf("&start_time",startIndex);
            var result = html.substring(startIndex,endIndex);
            console.log("result::" + result);
            var item = new Object();
            item.host=hostname;
            item.switch=result;
            switchArr.push(item);
        }
    });
}

for(var index3 in switchArr) {
    console.log(switchArr[index3].host+","+switchArr[index3].switch);
}








