/*
 * power_line_magnetic_field.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class power_line_magnetic_field {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mf", true);

    model.param().set("I0", "1000[A]");
    model.param().descr("I0", "\u7535\u529b\u7ebf\u7535\u6d41");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "power_line_magnetic_field.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{300, 300, 150});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{-150, -150, -50});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mf").feature("fsp1").set("sigma_stab_mat", "userdef");
    model.component("comp1").physics("mf").feature("fsp1")
         .set("sigma_stab", new String[]{"1e-3", "0", "0", "0", "1e-3", "0", "0", "0", "1e-3"});
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().set(1);
    model.component("comp1").physics("mf").create("edc1", "EdgeCurrent", 1);
    model.component("comp1").physics("mf").feature("edc1").selection().set(76, 85, 104);
    model.component("comp1").physics("mf").feature("edc1").set("Ie", "I0");
    model.component("comp1").physics("mf").create("edc2", "EdgeCurrent", 1);
    model.component("comp1").physics("mf").feature("edc2").selection().set(812, 830, 866);
    model.component("comp1").physics("mf").feature("edc2").set("Ie", "I0*exp(i*2*pi/3)");
    model.component("comp1").physics("mf").create("edc3", "EdgeCurrent", 1);
    model.component("comp1").physics("mf").feature("edc3").selection().set(1560, 1569, 1588);
    model.component("comp1").physics("mf").feature("edc3").set("Ie", "I0*exp(i*4*pi/3)");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u571f\u58e4");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0.5"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"10"});

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", 50);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u78c1\u573a\u6a21");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("edges", false);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("data", "dset1");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "0.1");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection()
         .set(14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 65, 66, 69, 70, 72, 73, 74, 75, 86, 87, 105, 106, 116, 118, 120, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 468, 469, 470, 471, 472, 473, 474, 475, 476, 478, 479, 480, 481, 482, 483, 485, 486, 487, 488, 489, 490, 491, 492, 493, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 509, 510, 511, 512, 513, 514, 515, 516, 517, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 813, 814, 815, 816, 817, 818, 819, 820, 828, 831, 832, 840, 842, 843, 844, 845, 846, 847, 848, 851, 852, 854, 856, 864, 867, 868, 876, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 910, 911, 912, 913, 914, 915, 916, 917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022, 1023, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031, 1032, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048, 1049, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1072, 1073, 1074, 1075, 1076, 1077, 1078, 1079, 1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087, 1088, 1089, 1090, 1091, 1092, 1093, 1094, 1095, 1096, 1097, 1098, 1099, 1100, 1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118, 1119, 1120, 1121, 1122, 1123, 1124, 1125, 1126, 1127, 1128, 1129, 1130, 1131, 1132, 1133, 1134, 1135, 1136, 1137, 1138, 1139, 1140, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1150, 1151, 1152, 1153, 1154, 1155, 1156, 1157, 1158, 1159, 1160, 1161, 1162, 1163, 1164, 1165, 1166, 1167, 1168, 1169, 1170, 1171, 1172, 1173, 1174, 1175, 1176, 1177, 1178, 1179, 1180, 1181, 1182, 1183, 1184, 1185, 1186, 1187, 1188, 1189, 1190, 1191, 1192, 1193, 1194, 1195, 1196, 1197, 1198, 1199, 1200, 1201, 1202, 1203, 1204, 1205, 1206, 1207, 1208, 1209, 1210, 1211, 1212, 1213, 1214, 1215, 1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241, 1242, 1243, 1244, 1245, 1246, 1247, 1248, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 1259, 1260, 1261, 1262, 1263, 1264, 1266, 1267, 1268, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277, 1278, 1279, 1280, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1289, 1290, 1291, 1292, 1293, 1294, 1295, 1296, 1297, 1298, 1299, 1300, 1301, 1302, 1303, 1304, 1305, 1306, 1307, 1308, 1309, 1310, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 1318, 1319, 1320, 1321, 1322, 1323, 1324, 1325, 1326, 1327, 1328, 1329, 1330, 1331, 1332, 1333, 1334, 1335, 1336, 1337, 1338, 1339, 1340, 1341, 1342, 1343, 1344, 1345, 1346, 1347, 1348, 1349, 1350, 1351, 1352, 1353, 1354, 1355, 1356, 1357, 1358, 1359, 1360, 1361, 1362, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1370, 1371, 1372, 1373, 1374, 1375, 1376, 1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389, 1390, 1391, 1392, 1393, 1394, 1395, 1396, 1397, 1398, 1399, 1400, 1401, 1402, 1403, 1404, 1405, 1406, 1407, 1408, 1409, 1410, 1411, 1412, 1413, 1414, 1415, 1416, 1417, 1418, 1419, 1420, 1421, 1422, 1423, 1424, 1425, 1426, 1427, 1428, 1429, 1430, 1431, 1432, 1433, 1434, 1435, 1436, 1437, 1438, 1439, 1440, 1441, 1442, 1443, 1444, 1445, 1446, 1447, 1448, 1449, 1450, 1451, 1452, 1453, 1454, 1455, 1456, 1457, 1458, 1459, 1460, 1461, 1462, 1463, 1464, 1465, 1466, 1467, 1468, 1469, 1470, 1471, 1472, 1473, 1474, 1475, 1476, 1477, 1478, 1479, 1480, 1481, 1482, 1483, 1484, 1485, 1486, 1487, 1488, 1489, 1490, 1491, 1492, 1493, 1494, 1495, 1496, 1497, 1498, 1499, 1500, 1501, 1502, 1503, 1504, 1505, 1506, 1507, 1508, 1509, 1510, 1511, 1512, 1513, 1514, 1515, 1516, 1517, 1518, 1519, 1553, 1554, 1557, 1558, 1570, 1571, 1589, 1590, 1599, 1600, 1601, 1602, 1604, 1606, 1608, 1610, 1611, 1612);
    model.result("pg1").run();
    model.result("pg1").feature("line1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("line1").feature("mtrl1").set("family", "steel");
    model.result("pg1").run();
    model.result("pg1").create("line2", "Line");
    model.result("pg1").feature("line2").set("data", "dset1");
    model.result("pg1").feature("line2").set("expr", "1");
    model.result("pg1").feature("line2").set("linetype", "tube");
    model.result("pg1").feature("line2").set("radiusexpr", "0.1");
    model.result("pg1").feature("line2").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line2").set("coloring", "uniform");
    model.result("pg1").feature("line2").set("color", "black");
    model.result("pg1").feature("line2").create("sel1", "Selection");
    model.result("pg1").feature("line2").feature("sel1").selection()
         .set(32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 67, 68, 71, 77, 78, 79, 80, 81, 82, 83, 84, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 107, 108, 109, 110, 111, 112, 113, 114, 115, 117, 119, 121, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 821, 822, 823, 824, 825, 826, 827, 829, 833, 834, 835, 836, 837, 838, 839, 841, 857, 858, 859, 860, 861, 862, 863, 865, 869, 870, 871, 872, 873, 874, 875, 877, 1520, 1521, 1522, 1523, 1524, 1525, 1526, 1527, 1528, 1529, 1530, 1531, 1532, 1533, 1534, 1535, 1536, 1537, 1538, 1539, 1540, 1541, 1542, 1543, 1544, 1545, 1546, 1547, 1548, 1549, 1550, 1551, 1552, 1555, 1556, 1559, 1561, 1562, 1563, 1564, 1565, 1566, 1567, 1568, 1572, 1573, 1574, 1575, 1576, 1577, 1578, 1579, 1580, 1581, 1582, 1583, 1584, 1585, 1586, 1587, 1591, 1592, 1593, 1594, 1595, 1596, 1597, 1598, 1603, 1605, 1607, 1609);
    model.result("pg1").run();
    model.result("pg1").create("line3", "Line");
    model.result("pg1").feature("line3").set("data", "dset1");
    model.result("pg1").feature("line3").set("expr", "1");
    model.result("pg1").feature("line3").set("coloring", "uniform");
    model.result("pg1").feature("line3").set("color", "black");
    model.result("pg1").feature("line3").create("sel1", "Selection");
    model.result("pg1").feature("line3").feature("sel1").selection()
         .set(76, 85, 104, 477, 494, 518, 812, 830, 866, 1249, 1265, 1288, 1560, 1569, 1588);
    model.result("pg1").run();
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("colortable", "ThermalWave");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("xnumber", "0");
    model.result("pg1").feature("strmsl1").set("znumber", "0");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u7535\u529b\u7ebf\u7684\u78c1\u573a");

    model
         .description("\u7535\u529b\u7ebf\u901a\u5e38\u7528\u4f5c\u8fdc\u8ddd\u79bb\u4f20\u8f93\u7535\u529b\u7684\u4e00\u79cd\u624b\u6bb5\u3002\u672c\u6559\u7a0b\u5bf9\u4f20\u8f93\u9ad8\u538b\u4e09\u76f8\u4ea4\u6d41\u7535\u7684\u94c1\u5854\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u8ba1\u7b97\u5176\u4ea7\u751f\u7684\u78c1\u573a\u3002\u4e3a\u4e86\u4fdd\u62a4\u94c1\u5854\u514d\u53d7\u96f7\u51fb\uff0c\u76f8\u7ebf\u4e0a\u65b9\u8bbe\u7f6e\u4e86\u4e24\u6761\u5c4f\u853d\u7ebf\u3002\n\n\u672c\u6a21\u578b\u4f7f\u7528\u201c\u78c1\u573a\u201d\u7269\u7406\u573a\u63a5\u53e3\u6765\u8ba1\u7b97\u78c1\u573a\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("power_line_magnetic_field.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
