<div class="col-md-5">
    <!-- general form elements -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title text-uppercase txt-light-gray txt-light-weight2">View Vehicle details</h3>
        </div>
        <script type="text/javascript">
            function search(type, search_key) {
                $.ajax({
                    url: "/grabmd/search",
                    method: 'POST',
                    data: {type: type, search_key: search_key}
                }).done(function (respond) {
                    alert(respond);
                });
            }
        </script>
        <!-- /.box-header -->
        <div class="result-box-wrapper">
            <!-- form start -->
            <div class="container-fluid display-result-contraller-wrapper">

                <form class="form-inline" role="form">
                    <div class="form-group">
                        <input type="text" class="form-control" id="vehicle_detail_search" name="vehicle_detail_search" autocomplete="off" placeholder="SEARCH" 
                               onkeyup="setTimeout(search($('#rsch_result_count_per_page').val(), $(this).val().trim()), 2000);">
                    </div>
                    <div class="form-group PL-10">
                        <label class="control-label label-overide reg-form-lable font-size-12 PR-10" for="rsch_result_count_per_page">SHOW</label>
                        <select id="rsch_result_count_per_page" name="rsch_result_count_per_page" class="form-control">
                            <option value="1">Vehicle Brand</option>
                            <option value="2">Vehicle Model</option>
                            <option value="3">Company</option>
                            <option value="areagroup">Area Group</option>
                        </select>
                    </div>
                </form>
            </div>
            <!-- Post Brand............................................................................................. -->
            <div class="post">
                <div class="user-block">
                    <div class="profile-circle post-brand">
                        <span>B</span>
                    </div>
                    <span class="username">
                        <a href="#">Brand Name</a>
                    </span>
                    <span class="description">Added - 7:30 PM today</span>
                </div>
                <!-- /.user-block -->
                <p class="txt-light-gray">
                    ID | Brand Name | Country

                </p>
                <ul class="list-inline">
                    <li><a href="#" class="link-black text-sm"><i class="fa fa-pencil-square-o margin-r-5"></i> Edit</a></li>
                </ul>                    
            </div>
            <!-- /.post.......................................................................................... -->

            <!-- Post Model............................................................................................. -->
            <div class="post">
                <div class="user-block">
                    <div class="profile-circle post-model">
                        <span>M</span>
                    </div>
                    <span class="username">
                        <a href="#">Model Name</a>
                    </span>
                    <span class="description">Added - 7:30 PM today</span>
                </div>
                <!-- /.user-block -->
                <p class="txt-light-gray">
                    ID | Brand | Model | Year

                </p>
                <ul class="list-inline">
                    <li><a href="#" class="link-black text-sm" data-toggle="modal" data-target="#view-vehicle-details-model"><i class="fa fa-eye-slash margin-r-5"></i> View</a></li>
                    <li><a href="#" class="link-black text-sm"><i class="fa fa-pencil-square-o margin-r-5"></i> Edit</a></li>

                </ul>                    
            </div>
            <!-- /.post.......................................................................................... -->

            <!-- Post Company............................................................................................. -->
            <div class="post">
                <div class="user-block">
                    <div class="profile-circle post-company">
                        <span>C</span>
                    </div>
                    <span class="username">
                        <a href="#">Company Name</a>
                    </span>
                    <span class="description">Added - 7:30 PM today</span>
                </div>
                <!-- /.user-block -->
                <p class="txt-light-gray">
                    ID | Company Name | 011-000 0000

                </p>
                <ul class="list-inline">
                    <li><a href="#" class="link-black text-sm" data-toggle="modal" data-target="#view-vehicle-details-model"  onclick="setModelTitle('view-vehicle-detail-model-title', 'Vehicle Company')"><i class="fa fa-eye-slash margin-r-5"></i> View</a></li>
                    <li><a href="#" class="link-black text-sm"><i class="fa fa-pencil-square-o margin-r-5"></i> Edit</a></li>

                </ul>                    
            </div>
            <!-- /.post.......................................................................................... -->

            <!-- Post Company............................................................................................. -->
            <div class="post">
                <div class="user-block">
                    <div class="profile-circle post-area">
                        <span>A</span>
                    </div>
                    <span class="username">
                        <a href="#">Area Group</a>
                    </span>
                    <span class="description">Added - 7:30 PM today</span>
                </div>
                <!-- /.user-block -->

                <ul class="list-inline">

                    <li><a href="#" class="link-black text-sm"><i class="fa fa-pencil-square-o margin-r-5"></i> Edit</a></li>

                </ul>                    
            </div>
            <!-- /.post.......................................................................................... -->
            
            <ul class = "pagination">
                <li><a href = "#">&laquo;</a></li>
                <li><a href = "#">1</a></li>
                <li><a href = "#">2</a></li>
                <li><a href = "#">3</a></li>
                <li><a href = "#">&raquo;</a></li>
            </ul>

        </div>
        <!-- /.box -->
    </div>
</div>