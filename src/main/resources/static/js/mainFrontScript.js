// Script Navbar

  $(document).ready(function(){
      $("#search").keyup(function(){
      
      var query=$(this).val();
      if(query!='')
      {
           $.ajax({
            type: "GET",
	          contentType: "application/json",
	          url: '/search/products?keyword='+query,
	          data: {},
	          dataType: 'json',
	          cache: false,
	          timeout: 600000,
	          success: function (data) {
              var json = data.result;
              console.log(json);
              $.each(json,function(index,product){
                if(product.galery !=null && product.category.title =='service'){
                  $('#dumppy').html('');    
                  $("nav div").hide();
                  $('#dumppy').append(
                  '<div class="row" style="margin-top:4.5rem;">'+ 
                      '<div class="col-xs-12">'
                      +'<img class="rounded-circle" style="width:80px; height:8px" src='+ data.awsBucketProduct+product.galery.id + '/'+ product.galery.image1 +' alt="picture">'
                    +'</div>'   
                    +'<div class="col-xs-12" style="display:inline;margin-left:1rem; cursor:pointer;" onclick="getResultDumppy('+product.id+')">'
                      +'<span class="text-success">' + product.name+'</span>'+'<br>'
                      +'<span class="text-danger">' +product.price+ ' xof'+'</span>' 
                    +'</div>'
                  +"</div>"   
                 );
                }
                if(product.galery !=null && product.category.title =='store'){
                  $('#dumppy').html('');    
                  $("nav div").hide();
                  $('#dumppy').append( 
                  '<div class="row" style="margin-top:4.5rem;">'+ 
                      '<div class="col-xs-12">'
                      +'<img class="rounded-circle" style="width:80px; height:80px" src='+ data.awsBucketProduct+product.galery.id + '/'+ product.galery.image1 +' alt="picture">'
                      +'</div>'   
                      +'<div class="col-xs-12" style="display:inline;margin-left:1rem;cursor:pointer;" onclick="getResultDumppy('+product.id+')">'
                        +'<span class="text-success">' + product.name+'</span>'+'<br>'
                        +'<span class="text-danger">' +product.price+' xof'+'</span>'
                      +'</div>'
                    +"</div>"   
                   
                 );
                }
                if(product.galery !=null && product.category.title =='shop'){
                  $('#dumppy').html('');    
                  $("nav div").hide();
                  $('#dumppy').append(
                  '<div class="row" style="margin-top:4.5rem;">'+ 
                      '<div class="col-xs-12">'
                      +'<img class="rounded-circle" style="width:80px; height:80px" src='+ data.awsBucketShop+product.galery.id + '/'+ product.galery.image1 +' alt="picture">'
                    +'</div>'   
                    +'<div class="col-xs-12" style="display:inline;margin-left:1rem;cursor: pointer;" onclick="getResultDumppy('+product.id+')">'
                      +'<span class="text-success">' + product.name+'</span>'+'<br>'
                      +'<span class="text-danger">' +product.price+' xof'+'</span>' 
                    +'</div>'
                   
                  +"</div>"   
               );
              }
              if(product.galery !=null && product.category.title == 'groupSale'){
                $('#dumppy').html('');    
                $("nav div").hide();
                  $('#dumppy').append(
                  '<div class="row" style="margin-top:4.5rem;">'+ 
                      '<div class="col-xs-12">'
                      +'<img class="rounded-circle" style="width:80px; height:80px" src='+ data.awsBucketGroupSale+product.galery.id + '/'+ product.galery.image1 +' alt="picture">'
                    +'</div>'   
                    +'<div class="col-xs-12" style="display:inline; margin-left:1rem; cursor:pointer;" onclick="getResultDumppy('+product.id+')">'
                      +'<span class="text-success">' + product.name+'</span>'+'<br>'
                      +'<span class="text-danger">' +product.price+' xof'+'</span>' 
                    +'</div>'  
                  +"</div>"   
               );
              }
              
            });
          },
             
	          error: function (e) {
	            console.log("ERROR : ", e);
	          }
          });
      }
      });
  });

 


   function getResultDumppy(productId){
    	$.ajax({
			type: "GET",
	        contentType: "application/json",
	        url: '/search/findProduct?product_id='+productId,
	        data: {},
	        dataType: 'json',
	        cache: false,
	        timeout: 600000,
	        success: function (data) {
            window.location.href = '/search/resultsProduct/'+data.id+'/'+data.name;
	        },
	        error: function (e) {
	            console.log("ERROR : ", e);
	        }
		});  
     }


//End Script Navbar

//Script Header Menu

	var w = window.innerWidth;
	var h = window.innerHeight;
	
	console.log("Width: " +w);
	console.log("Height: " +h);
  
 
  if(w>767 && w<1025){
    console.log("Tablet device detected");
    advertiseTablet();
  }

  if(w>1024){
    console.log("PC Device detected");
    advertisePc();
  }

  function advertiseTablet(){
		  $.ajax({
			  type: "GET",
			  contentType: "application/json",
			  url: '/advertise/rest/tablet',
			  data: {},
			  dataType: 'json',
			  cache: false,
			  timeout: 600000,
			  success: function (data) {
          $("#advertiseMainImg").attr("src",data.image);   
			  },
			  error: function (e) {
				  console.log("ERROR : ", e);
			  }
		  }); 
		}
  
    function advertisePc(){
		  $.ajax({
			  type: "GET",
			  contentType: "application/json",
			  url: '/advertise/rest/pc',
			  data: {},
			  dataType: 'json',
			  cache: false,
			  timeout: 600000,
			  success: function (data) {
          $("#advertiseMainImg").attr("src",data.image);   
			  },
			  error: function (e) {
				  console.log("ERROR : ", e);
			  }
		  }); 
		}
  	
//End Script Header Menu

//Script Service
 loadServices();
  function loadServices(){
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: '/company/services',
            data: {},
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                var json = data.companies;
                if(json){
                    $.each(json,function(index,company){
            getServiceLikes(company.id);
                        getSpecialities(company.id);
                    });
                }
            
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        }); 
      }
    function getServiceLikes(service_id){
          $.ajax({
              type: "GET",
              contentType: "application/json",
              url: '/like/service/count?service_id='+service_id,
              data: {},
              dataType: 'json',
              cache: false,
              timeout: 600000,
              success: function (data) {
                  $('#resultLikeService-'+data.companyId).html(data.numberLikes);
              },
              error: function (e) {
                  console.log("ERROR : ", e);
              }
          }); 
      }
  function getSpecialities(service_id){
          $.ajax({
              type: "GET",
              contentType: "application/json",
              url: '/company/service/specialities?service_id='+service_id,
              data: {},
              dataType: 'json',
              cache: false,
              timeout: 600000,
              success: function (data) {
                var json = data.specialities;
                  $.each(json,function(index,speciality){					  
                    $('#resultSpeciality-'+service_id).append(
                       '<div style="margin-top:-1rem !important;">'
                         +'<span style="margin-top: -1rem !important;font-size: 10px; display: inline-block !important; width: 125px !important; overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">'
                          +"-"+speciality.name 
                         +'</span>'
                       +'</div>'
                   );
                  });
              },
              error: function (e) {
                  console.log("ERROR : ", e);
              }
          }); 
      }
    
  function likeService(service_id){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/like/service?service_id='+service_id,
        data: {},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#resultLikeService-'+data.companyId).html(data.numberLikes);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    }); 
  }

  //End Script Service--

//Script Shop - Store - GroupSale - Footer--
  loadStores();

    function loadStores(){
      $.ajax({
          type: "GET",
          contentType: "application/json",
          url: '/company/stores',
          data: {},
          dataType: 'json',
          cache: false,
          timeout: 600000,
          success: function (data) {
              var json = data.companies;
              console.log(json);
              if(json){
                  $.each(json,function(index,company){
                      getStoreLikes(company.id);
                  });
              }
          
          },
          error: function (e) {
              console.log("ERROR : ", e);
          }
      }); 
    }
  
    function getStoreLikes(store_id){
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: '/like/store/count?store_id='+store_id,
            data: {},
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                $('#resultLikeStore-'+data.companyId).html(data.numberLikes);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        }); 
    }

    function likeStore(store_id){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/like/store?store_id='+store_id,
        data: {},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#resultLikeStore-'+data.companyId).html(data.numberLikes);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    }); 
  }	
  
function readMoreRuleAtooly(){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/contact/atooly/readMoreRule',
        data: {},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#resultReadMoreRule').html(data.contactrule);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });  
}

 
var productId;
$(document).ready(function(){
    $("#quickViewModal").modal({
        keyboard: true,
        backdrop: "static",
        show: false,
        
    }).on("show.bs.modal", function(event){
        var button        = $(event.relatedTarget);
        var id            = button.data("id");
        var name          = button.data("name");
        var detail        = button.data("detail");
        var upprice       = button.data("upprice");
        var price         = button.data("price");
        var category      = button.data("category");
        var quantity      = button.data("quantity");
        var subcategory   = button.data("subcategory");
        var deliveryprice = button.data("deliveryprice");
        var image1        = button.data('image1');
        var image2        = button.data('image2');
        var image3        = button.data('image3');
        var image4        = button.data('image4');
        var detail        = button.data("detail");
        //var companyname   = button.data('companyname');
        //var companyimage  = button.data('companyimage');
        
        $(this).find("#id").html($("<b> " +id+ "</b>"));
        $(this).find("#name").html($("<b> " + name +"</b>"));
        $(this).find("#detail").html($("<li> " + detail +"</li>"));
        $(this).find("#upprice").html($("<b> " + upprice + " FCFA"  +"</b>"));
        $(this).find("#price").html($("<b> " + price + " FCFA" +"</b>"));
        $(this).find("#category").html($("<span>" + category +"</span> "));
        $(this).find("#subcategory").html($("<span>" + subcategory +"</span> "));
        $(this).find("#quantity").html($("<span> " + quantity +"</span>"));
        $(this).find("#deliveryprice").html($("<b> " + deliveryprice + " FCFA" +"</b>"));
        $(this).find("#image1").attr("src", image1);
        $(this).find("#image2").attr("src", image2);
        $(this).find("#image3").attr("src", image3);
        $(this).find("#image4").attr("src", image4);
        //$(this).find("#companyimage").attr("src", companyimage);
        //$(this).find("#companyname").html($("<span>" + companyname + "</span>"));
        productId=id;

    })
});


var productId;
$(document).ready(
    function(){
    
    $("#quickViewModalAttoly").modal({
        keyboard: true,
        backdrop: "static",
        show: false,
        
    }).on("show.bs.modal", function(event){
        var button        = $(event.relatedTarget);
        var id            = button.data("id");
        var name          = button.data("name");
        var detail        = button.data("detail");
        var price         = button.data("price");
        var category      = button.data("category");
        var detail        = button.data("detail");
        var quantity      = button.data("quantity");
        var closeddate    = button.data("closeddate");
        var currentbooking= button.data("currentbooking"); 
        var deliveryprice = button.data("deliveryprice");
        var image1        = button.data('image1');
        var image2        = button.data('image2');
        var image3        = button.data('image3');
        var image4        = button.data('image4');
      
        
        $(this).find("#id").html($("<b> " +id+ "</>"));
        $(this).find("#name").html($("<b> " + name +"</b>"));
        $(this).find("#detail").html($("<li> " + detail +"</li>"));
        $(this).find("#price").html($("<b> " + price + " FCFA" + "</b>"));
        $(this).find("#quantity").html($("<span> " + quantity +"</span>"));
        $(this).find("#category").html($("<span>" + category +"</span> "));
        $(this).find("#closeddate").html($("<span> " + closeddate +"</span>"));
        $(this).find("#currentbooking").html($("<span>" + currentbooking +"</span> "));
        $(this).find("#deliveryprice").html($("<b> " + deliveryprice   + " FCFA"  + "</b>"));
        $(this).find("#image1").attr("src", image1);
        $(this).find("#image2").attr("src", image2);
        $(this).find("#image3").attr("src", image3);
        $(this).find("#image4").attr("src", image4);
        productId=id;
       
    })
});
function bookNow(){
    
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/booking/product?product_id='+productId,
        data: {},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            //   window.location.href = data.name+'booking/add/'+data.id;
            window.location.href = '/booking/add/'+data.id;
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });  
 }



function sendProductId(id){
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/product/sendProductId?id='+id,
        data: {},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#productId').html(data.id);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}


function changeImage(a){
var idImage    = a.id;
var image = a.src;
$.ajax({
    type: "GET",
    contentType: "application/json",
    url: '/product/changeImage?image='+image,
    data: {},
    dataType: 'json',
    cache: false,
    timeout: 600000,
    success: function (data) {
        $("#image1").attr("src",data.brand);    
    },
    error: function (e) {
        console.log("ERROR : ", e);
    }
});
} 

function companyImage(a){  
 var image = a.src;
 $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/company/companyImage?image='+image,
        data: {},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
         window.location.href = data.name+'company/info/'+data.id;
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}  

function companyImageAttoly(a){  
 var image = a.src;
 $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/company/companyImageAttoly?image='+image,
        data: {},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
          window.location.href = data.name+'/contact';
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}  


function changeImageAttoly(a){
    var idImage    = a.id;
    var image = a.src;
    
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '/product/changeImage?image='+image,
        data: {},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $(".attoly #image1").attr("src",data.brand);   
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
 } 
//End Script shop-store-groupSale-Footer