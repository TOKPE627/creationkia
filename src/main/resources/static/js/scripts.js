/**
 * 
 */
function checkPasswordMatch() {
   alert("hommmmmm");
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();
	
	if(password == "" && confirmPassword =="") {
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	} else {
		if(password != confirmPassword) {
			$("#checkPasswordMatch").html("Passwords do not match!");
			$("#updateUserInfoButton").prop('disabled', true);
		} else {
			$("#checkPasswordMatch").html("Passwords match");
			$("#updateUserInfoButton").prop('disabled', false);
		}
	}
}

$(document).ready(function() {
	$('.delete-ad').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer cette annonce? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	
	

	
	$('#deleteSelected').click(function() {
		var idList= $('.checkboxAd');
		var adIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				adIdList.push(idList[i]['id'])
			}
		}
		
		console.log(adIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Etes-vous surs de supprimer toutes les annonces sélectionnés? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(adIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});
	
	$('#removeAdList').click( function(){
	   	var ad_id = $('#adId_txt').val();
	   	    bootbox.confirm({
			message: "Etes-vous surs de supprimer toutes les photos? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						data: {},
						url: 'removeList?ad_id='+ad_id,
						contentType: "application/json",
						success: function(res) {
							console.log(res);
							alert(res);
                            //window.location.href = data.redirect;							
						},
						error: function(res){
							console.log(res); 
							}
					});
				}
			}
		});
	   
	});
	
	$("#selectAllAds").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxAd").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxAd").prop("checked",false);
		}
		})
	});
	
	
/*	Store*/
		$('.delete-store').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer ce magasin? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	
	
	
	$('#deleteSelectedAllStores').click(function() {
		var idList= $('.checkboxStore');
		var storeIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				storeIdList.push(idList[i]['id'])
			}
		}
		
		console.log(storeIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Etes-vous surs de supprimer tous les magasins sélectionnés? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(storeIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});
	
	$("#selectAllStores").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxStore").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxStore").prop("checked",false);
		}
	})
	
	
/*	Category*/
		$('.delete-category').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'/category/remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer cette categorie? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	

	
/*	SubCategory*/
		$('.delete-subCategory').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'/subCategory/remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer cette sous-categorie? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	

	
	
/*	Product*/
		$('.delete-product').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'/product/remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer ce produit? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	/*	Advertise*/
		$('.delete-advertise').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'/advertise/remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer cette affiche? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	
	
	/*	Catalog*/
		$('.delete-catalog').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'/catalog/remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer ce catalogue? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});

	
	/*	Speciality*/
		$('.delete-speciality').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'/speciality/remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer cette spécialité? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
		/*	TownAvailable*/
		$('.delete-townAvailable').on('click', function (){
		/*<![CDATA[*/
	    var path = '/townAvailable/remove';
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer cette ville? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
			/*	TownAvailable*/
		$('.delete-day').on('click', function (){
		/*<![CDATA[*/
	    var path = '/remove';
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Etes-vous surs de supprimer ce jour? Cette action est irréversible.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Annuler'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirmer'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	
 $('#contactPublisher').on('show.bs.modal',function(event){
        console.log('Modal for Contact Opened');
        var button=$(event.relatedTarget)
        var modal=$(this)
        var publisher=button.data('publisher')
        var contact  =button.data('contact') 
        modal.find('.modal-body #publisher').val(publisher)
        modal.find('.modal-body #contact').val(contact)
 })