# 商业地产新开18城58房源导入Job设计

```
58房源需要导入商业地产，一期不走中间件和上下架，build solr时候直接从队列表中获取data，往solr里build
```

## 1. 写字楼出租solr上下架

```
写字楼出租solr上架与下架【往solr里build和删除solr对应数据】
```

### 1.1 上架

. $i_house = jinpu_db.i_house->findRow(args['house_id'])

. $house_info = jinpu_db.e_office_rent->findRow($i_house->id)

. $house_type = $i_house->house_type,按照$house_type去build四类solr上下架

. $e_office_rent_info = jinpu_db.e_office_rent->findRow($i_house->id)，如果$e_office_rent_info为空，则直接返回错误码，丢弃本条数据的solr上架

. $solr['id'] = $i_house->id;

. $solr['title'] = $house_info->title;

. $solr['building_id'] = $house_info->building_id;

. $solr['member_id'] = $house_info->member_id;

. $solr['city_id'] = $house_info->city_id;

. $solr['floor_id'] = $house_info->floor_id;

. $solr['area'] = $house_info->area;

. $solr['monthly_rent'] = $house_info->monthly_rent;

. $solr['daily_rent']  = $house_info->daily_rent;

. $solr['create_time'] = $house_info->list_time;

. $solr['is_quality'] = $house_info->is_quality;

. $solr['is_exquisite'] = $house_info->is_exquisite;

. $solr['publish_date'] = date("Y-m-d", strtotime($solr['create_time']));

. $solr['publish_time'] = date("H-i-s", strtotime($solr['create_time']));

. $solr['house_high_level'] = ($house_info->floor_id == 3) ? 1 : 0;

. $solr['img_num'] = count(jinpu_db.e_office_rent_img_v2->find($i_house))

. $solr['monthly_rent_id'] = jinpu_db.d_filter_monthly_rent->findRow($house_info->city_id,$house_info->monthly_rent)->result_id ? 1 : 0

. $solr['daily_rent_id'] = jinpu_db.d_filter_daily_rent->find($house_info->city_id,$house_info->monthly_rent)->result_id ? 1 : 0

. $solr['area_id'] = ($rent_area_id = jinpu_db.d_filter_area_rent->findRow($house_info->area)->id) ? $rent_area_id : 0,

. $e_building = ($building_level_id = jinpu_db.e_building->findRow($house_info->building_id)->building_level_id) ? $building_level_id : 0

. if (empty($e_building)) {
      $e_building = jinpu_db.e_property->findById($house_info->building_id)
      if (empty($e_building)) {
          return 'NULL';
      }
  }

. $solr['building_name'] = $e_building->name

. $solr['match_building'] = $e_building->name

. $solr['address'] = $e_building->address

. $solr['district_id'] = $e_building->district_id

. $solr['block_id'] = $e_building->block_id

. $solr['building_level_id'] = $e_building->building_level_id

. $solr['house_new_complete'] = $this->isNewCompleteHouse($e_building)

. Rank

    + $rank = jinpu_db.e_house_rank->findById($i_house->id)

    + $solr['final_score'] = $rank->final_score

    + $solr['random_score'] = $rank->random_score

    + $solr['stage'] = $rank->stage

    + $solr['sub_stage'] = $rank->sub_stage

. $solr['is_developer'] = 0

. $solr['house_heat'] = 0

. $solr['house_metro'] = 0

. Geo

    + $building_map = jinpu_db.e_map_property->findById($house_info->building_id), array('blat' => xxx, 'blng' => '', 'position' => '$lat, $lng')

    + $solr['blat'] = $building_map['blat'];

    + $solr['blng'] = $building_map['blng'];

    + $solr['position'] = $building_map['position'];

. company && store

    + $member_ext = jinpu_db.e_member_ext->findRow($solr['member_id']);

    + $solr['company_id'] = $member_ext['company_id'];

    + $solr['store_id'] = $member_ext['store_id'];

. Block

    + $block = $this->getNewDistrictBusiness($e_building->block_id); 请参考Shangpu_Core_Spread_Service_OfficeRentService.getNewDistrictBusiness()

    + $solr['new_district_id'] = $block['new_district_id'];

    + $solr['new_district_name'] = $block['new_district_name'];

    + $solr['new_business_id'] = $block['new_business_id'];

    + $solr['new_business_name'] = $block['new_business_name'];

    + $solr['is_developer'] = 0;

    + $solr['house_heat'] = 0;

    + $solr['house_metro'] = 0;

    + $solr['bid_rank'] = !empty($house_info->spread_offer) ? $house_info->spread_offer : 0;

    + $solr['bid_time'] = !empty($house_info->spread_offer_time) ? $house_info->spread_offer_time : 0;

    + $spread_info = $this->getESpreadInfo($i_house->bid_spread_id);

    + $solr['list_time'] = $spread_info['list_time'];

    + $solr['bid_ratio'] = $spread_info['bid_ratio'];

    + $solr['roll_ratio'] = $spread_info['roll_ratio'];

##### 1.2 下架




