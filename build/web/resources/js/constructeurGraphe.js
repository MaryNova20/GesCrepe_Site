/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function construireGrapheColumn3D(categorie, donnees, titre, description, nomCategorie, titreY){
    $('#container').highcharts({
        chart: {
            type: 'column',
            margin: 85,
            options3d: {
                enabled: true,
                alpha: 10,
                beta: 0,
                depth: 80
            }
        },
        title: {
            text: titre
        },
        subtitle: {
            text: description
        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        xAxis: {
            categories: categorie
        },
        yAxis: {
            title: {
                text: titreY
            }
        },
        series: [{
            name: nomCategorie,
            data: donnees
        }]
    });
}

function camambergClassic(titre, donnees, idConteneur){
    
    $('#'+idConteneur).highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: titre
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series : donnees
//        series: [{
//            name: "Brands",
//            colorByPoint: true,
//            data: [{
//                name: "Microsoft Internet Explorer",
//                y: 56.33
//            }, {
//                name: "Chrome",
//                y: 24.03,
//                sliced: true,
//                selected: true
//            }, {
//                name: "Firefox",
//                y: 10.38
//            }, {
//                name: "Safari",
//                y: 4.77
//            }, {
//                name: "Opera",
//                y: 0.91
//            }, {
//                name: "Proprietary or Undetectable",
//                y: 0.2
//            }]
//        }]
    });
}

function camamberg3D(titre, donnees, idConteneur){
    $('#'+idConteneur).highcharts({
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            }
        },
        title: {
            text: titre
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: donnees
    });
}

function deformater(nombre){
    var tab = nombre.trim().split("");
    var resultat = "";
    for(var i=0,c=tab.length;i<c;i++){
        if(tab[i] !== " "){
            resultat += tab[i];
        }
    }
    return parseFloat(resultat);
}