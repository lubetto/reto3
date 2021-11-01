function traerInformacionReservation(){
    $.ajax({
        url:"http://129.151.109.134:8080/api/Reservation/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta);
            pintarRespuestaReservation(respuesta);
        }
    });
}

function pintarRespuestaReservation(items){
    let myTable="<table>";
    for(f=0;f<items.length;f++){
        myTable+="<tr>";
        myTable+="<td>" +items[f].startDate+"</td>";
        myTable+="<td>" +items[f].devolutionDate+"</td>";
        myTable+="<td> <button onclick='actualizarInformacionReservation("+items[f].idReservation+")'>Actualizar</button> </td>";
        myTable+="<td> <button onclick='borrarInformacionReservation("+items[f].idReservation+")'>Borrar</button> </td>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultadoReservation").html(myTable);
}

function guardarInformacionReservation(){
    let myData={
        startDate:$("#StartDate").val(),
        devolutionDate:$("#DevolutionDate").val()
    };

    $.ajax({
        url: "http://129.151.109.134:8080/api/Reservation/save",
        type:"POST",
        contentType: "application/json; charset=utf-8",
        data:myData,
        data: JSON.stringify(myData),
        datatype:"JSON",

        success:function(respuesta){
            $("#resultadoReservation").empty();
            $("#StartDate").val("");
            $("#DevolutionDate").val("");
            traerInformacionReservation();
            alert("se ha guardado el dato");
        }
    });
}

function actualizarInformacionReservation(idElemento){
    let myData1={
        idReservation:idElemento, //
        startDate:$("#StartDate").val(),
        devolutionDate:$("#DevolutionDate").val()
    };
    console.log(myData1);
    let dataToSend=JSON.stringify(myData1);
    $.ajax({
        url:"http://129.151.109.134:8080/api/Reservation/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",

        success:function(respuesta){
            $("#resultadoReservation").empty();
            $("#StartDate").val("");
            $("#DevolutionDate").val("");
            traerInformacionReservation();
            alert("se ha guardado el dato");
        }
    });

}

function borrarInformacionReservation(idElemento){

    $.ajax({
        url: "http://129.151.109.134:8080/api/Reservation/"+idElemento,
        type:"DELETE",
        contentType:"application/JSON",
        datatype:"JSON",

        success:function(respuesta){
            traerInformacionReservation();
            alert("se ha guardado el dato");
        }
    });
}