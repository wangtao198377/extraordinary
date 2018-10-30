var taskNames = {
    'task_0010_split_splitDb_delete':1
};

// morderbank 为 mysql
var prefix = 'morderbank-';
var start = 1,end = 24;

// 0:关闭 1:启动
var yn = 0;
// 0/10 * * * * ? 10秒一次
// 0 0/5 * * * ?  5分钟一次
var cronexpression = '0/5 * 3-6 * * ?';
var partitionIdsStr = '0,1,2,3,4,5,6,7,8,9'

for (var i = start;i <= end ;i++) {
    $.ajax({
        type: "POST",
        url: "/rest/workerType/doTree.json",
        data: {showAllWorker:false, pCode:prefix + (i < 10 ? '00' + i : '0' + i )},
        dataType:"json",
        success: function(data, textStatus){
            for(var j = 0; j < data.length;j++ )
            {
                var item = data[j].attr;
                if(!taskNames[item.workerTypeName]){
                    continue;
                }

                var req = new Object();
                req.workerTypeCode = item.workerTypeCode;
                req.parentTypeCode = item.parentTypeCode;
                req.workerTypeName = item.workerTypeName;
                req.wtype = item.wtype;
                req.passTimeNum = item.passTimeNum;
                req.failMaxNum = item.failMaxNum;
                req.waitOrders = item.waitOrders;

                req.yn = yn;
                req.cronexpression = cronexpression;
                req.partitionIdsStr = partitionIdsStr;

                $.ajax({
                    type: "POST",
                    url: "/rest/workerType/doUpdateWorkerType.json?_charset_=utf-8",
                    data:req,
                    dataType:"json",
                    success: function(data, textStatus){
                        console.log(data);
                    },
                    error:function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(textStatus);
                    }
                });
            }
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            alert(textStatus);
        }
    });
}