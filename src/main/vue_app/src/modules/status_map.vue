<script>
  /**
   *  状态转换
   */

  const fm_status = [
    {value: 0, label: '待审核'},
    {value: 1, label: '正常'},
    {value: 2, label: '审核未通过'},
    {value: 3, label: '禁用中'},
    {value: 4, label: '全部'}
  ]

  const fm_type = [
    {value: 0, label: ''},
    {value: 1, label: '个人'},
    {value: 2, label: '企业'},
  ]

  const status_map = {
      vFmStatus: fm_status,

      fmStatus : function (code) {
        return fm_status[code].label
      },
      fmType : function (code) {
        return fm_type[code].label
      }
  }

  export default status_map
</script>
