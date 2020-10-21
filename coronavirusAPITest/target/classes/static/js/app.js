app= (function(){

    function GetCountries(){
        api.getAllcountries(showCountries)
    }

    var  num=0;

    function showCountries(countries){
        var mapCountries = countries.map(
            function(countries) {
                var countryname = countries.countryname;
                var confirmed = countries.confirmed;
                var deaths = countries.deaths;
                var recovered = countries.recovered;
                return{countryname: countryname, confirmed: confirmed, deaths: deaths, recovered:recovered}
            }
        )
        var table = document.getElementById("countries");
        console.log(table);
        if (table.rows.length > 1) for(var i = table.rows.length - 1; i > 0; i--) {table.deleteRow(i);}
        $('#countries').append('<tbody>');
        mapCountries.map(function (countries){
            var onclick = "app.showprovince( \""+ countries.countryname +","+ countries.deaths +","+ countries.confirmed +","+ countries.recovered +"\" )";
            var refbutton = "<button onclick='"+ onclick +"'>"+countries.countryname+"</button>";
            var row = '<tr style="border-radius: 20px;"><th>'+refbutton+'</th><th>'+countries.deaths+'</th><th>'+countries.confirmed+'</th><th>'+countries.recovered+'</th></tr>';
            $('#countries').append(row);
        })
        $('#countries').append('</tbody>');
    }


    function showprovince(coountrieinfo){
        if(num==1){
            $('#provinces-name').text("");
            $('#provinces-deaths').text("Num Deaths:");
            $('#provinces-infec').text("Num infected:");
            $('#provinces-cured').text("Num Cured:");
            num = 0;
        }
        var info = coountrieinfo.split(",");
        $('#provinces-name').append(info[0]);
        $('#provinces-deaths').append(info[1]);
        $('#provinces-infec').append(info[2]);
        $('#provinces-cured').append(info[3]);
        num = 1;
        api.getProvinceByCountrie(info[0],showprovincies);
    }

    function showprovincies(provinces){

        var mapProvinces = provinces.map(
            function(provinces) {
                var name = provinces.province;
                var confirmed = provinces.confirmed;
                var deaths = provinces.deaths;
                var recovered = provinces.recovered;
                return{name: name, confirmed: confirmed, deaths: deaths, recovered:recovered}
            }
        )
        var table = document.getElementById("provinces");
        console.log(table);
        if (table.rows.length > 1) for(var i = table.rows.length - 1; i > 0; i--) {table.deleteRow(i);}
        $('#provinces').append('<tbody>');
        mapProvinces.map(function (provinces){
            var row = '<tr style="border-radius: 20px;"><th>'+provinces.name+'</th><th>'+provinces.deaths+'</th><th>'+provinces.confirmed+'</th><th>'+provinces.recovered+'</th></tr>';
            $('#provinces').append(row);
        })
        $('#provinces').append('</tbody>');

    }

    return{
        GetCountries:GetCountries,
        showprovince:showprovince
    }

})();