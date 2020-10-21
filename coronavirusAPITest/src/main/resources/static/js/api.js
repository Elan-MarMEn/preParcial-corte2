api= (function(){
    function getAllcountries(callback){
        $.ajax ({
            dataType: "json",
            url: "http://localhost:8080/covid19/countries",
            success: function(data){
            callback(data)
            }
        });
    }

    function getProvinceByCountrie(name,callback){
        $.ajax ({
            dataType: "json",
            url: "http://localhost:8080/covid19/countries/"+name,
            success: function(data){
            callback(data)
            }
        });
    }

return{
    getAllcountries:getAllcountries,
    getProvinceByCountrie:getProvinceByCountrie

}


})();