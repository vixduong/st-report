package vn.com.seatechit;

public class BuildReport {
//  public static void main(String[] args) throws IOException {
//    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    try(InputStream is = new ClassPathResource("templates/RP_Searching_Folder_Result.xlsx").getInputStream()) {
//      try (OutputStream os = Files.newOutputStream(Paths.get("target/RP_output.xlsx"))) {
//        SearchingFolderResult search = generateResult();
//        Context context = new Context();
//        context.putVar("location", search.getLocation());
//        context.putVar("day", search.getDay());
//        context.putVar("month", search.getMonth());
//        context.putVar("year", search.getYear());
//        context.putVar("fromDate", Objects.nonNull(search.getFromDate()) ? fmt.format(search.getFromDate()): "");
//        context.putVar("toDate", Objects.nonNull(search.getToDate()) ? fmt.format(search.getToDate()): "");
//        context.putVar("items", search.getItems());
//        JxlsHelper.getInstance().processTemplate(is, os, context);
//      }
//    }
//  }
//
//  private static SearchingFolderResult generateResult() {
//    return SearchingFolderResult.builder()
//        .day("01").month("01").year("2020")
//        .location("HN")
//        .fromDate(LocalDateTime.now())
//        .items(Arrays.asList(
//            SearchingFolderResultItem.builder()
//                .index(1)
//                .folderName("folder1").businessName("business1").businessCode("business1")
//                .customerType("customerType1").customerCode("customerCode1").customerName("customerName1")
//                .identification("identification1").mobilePhone("mobilePhone1")
//                .createdAt(LocalDateTime.now()).build(),
//            SearchingFolderResultItem.builder()
//                .index(2)
//                .folderName("folder2").businessName("business2").businessCode("business2")
//                .customerType("customerType2").customerCode("customerCode2").customerName("customerName2")
//                .identification("identification2").mobilePhone("mobilePhone2")
//                .createdAt(LocalDateTime.now()).build()
//        ))
//        .build();
//  }
}
