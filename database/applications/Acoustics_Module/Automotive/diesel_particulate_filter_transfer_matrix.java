/*
 * diesel_particulate_filter_transfer_matrix.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class diesel_particulate_filter_transfer_matrix {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "1200[Hz]", "\u6700\u5927\u9891\u7387");
    model.param().set("c_air", "343[m/s]", "\u58f0\u901f");
    model.param().set("lambda_air", "c_air/f0", "f0 \u7684\u6ce2\u957f");
    model.param().set("PortName", "1", "\u7aef\u53e3\u626b\u63cf\u53c2\u6570");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("h_block", "3[mm]");
    model.param("par2").set("hole_size", "1.5[mm]");
    model.param("par2").set("support_size", "1.5[mm]");
    model.param("par2").set("wall_size", "0.5[mm]");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "diesel_particulate_filter_transfer_matrix_full_system_geometry.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").set("porosity", new String[]{"0.46"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("PoroacousticsModel", "PoroacousticsModel", "Poroacoustics_model");
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("Rf", new String[]{"10900"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel")
         .set("Lth", new String[]{"110[um]"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("Lv", new String[]{"105[um]"});
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("tau", new String[]{"1"});

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(8, 425, 842);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3")
         .set(10, 13, 15, 17, 20, 22, 24, 26, 28, 31, 33, 35, 37, 39, 41, 43, 46, 48, 50, 52, 54, 56, 58, 60, 62, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 88, 90, 92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 115, 117, 119, 121, 123, 125, 127, 129, 131, 133, 135, 137, 139, 141, 143, 146, 148, 150, 152, 154, 156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 181, 183, 185, 187, 189, 191, 193, 195, 197, 199, 201, 203, 205, 207, 209, 211, 213, 215, 217, 220, 222, 224, 226, 228, 230, 232, 234, 236, 238, 240, 242, 244, 246, 248, 250, 252, 254, 256, 258, 260, 263, 265, 267, 269, 271, 273, 275, 277, 279, 281, 283, 285, 287, 289, 291, 293, 295, 297, 299, 301, 303, 305, 308, 310, 312, 314, 316, 318, 320, 322, 324, 326, 327, 329, 331, 333, 335, 337, 339, 341, 343, 345, 347, 349, 351, 353, 355, 357, 359, 361, 363, 364, 366, 368, 370, 372, 374, 376, 378, 381, 383, 385, 387, 389, 391, 393, 395, 397, 399, 401, 403, 405, 407, 409, 411, 413, 415, 417, 418, 420, 422, 424, 843, 845, 846, 848, 850, 852, 853, 855, 857, 859, 861, 863, 864, 866, 868, 870, 872, 874, 876, 878, 879, 881, 883, 885, 887, 889, 891, 893, 895, 897, 898, 900, 902, 904, 906, 908, 910, 912, 914, 916, 918, 920, 921, 923, 925, 927, 929, 931, 933, 935, 937, 939, 941, 943, 945, 947, 948, 950, 952, 954, 956, 958, 960, 962, 964, 966, 968, 970, 972, 974, 976, 978, 979, 981, 983, 985, 987, 989, 991, 993, 995, 997, 999, 1001, 1003, 1005, 1007, 1009, 1011, 1013, 1014, 1016, 1018, 1020, 1022, 1024, 1026, 1028, 1030, 1032, 1034, 1036, 1038, 1040, 1042, 1044, 1046, 1048, 1050, 1052, 1053, 1055, 1057, 1059, 1061, 1063, 1065, 1067, 1069, 1071, 1073, 1075, 1077, 1079, 1081, 1083, 1085, 1087, 1089, 1091, 1093, 1095, 1096, 1098, 1100, 1102, 1104, 1106, 1108, 1110, 1112, 1114, 1116, 1118, 1120, 1122, 1124, 1126, 1128, 1130, 1132, 1134, 1136, 1138, 1140, 1141, 1143, 1145, 1147, 1149, 1151, 1153, 1155, 1157, 1159, 1162, 1164, 1166, 1168, 1170, 1172, 1174, 1176, 1178, 1180, 1182, 1184, 1186, 1188, 1190, 1192, 1194, 1196, 1199, 1201, 1203, 1205, 1207, 1209, 1211, 1213, 1214, 1216, 1218, 1220, 1222, 1224, 1226, 1228, 1230, 1232, 1234, 1236, 1238, 1240, 1242, 1244, 1246, 1248, 1250, 1253, 1255, 1257);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4")
         .set(2, 3, 6, 7, 10, 11, 14, 15, 18, 19, 22, 23, 27, 28, 31, 32, 35, 37, 44, 47, 58, 62, 77, 82, 101, 107, 130, 137, 164, 172, 203, 212, 247, 257, 296, 307, 350, 362, 409, 422, 473, 487, 542, 557, 616, 632, 695, 712, 779, 797, 868, 887, 962, 982, 1061, 1082, 1165, 1187, 1274, 1297, 1388, 1412, 2115, 2116, 2119, 2121, 2128, 2131, 2142, 2146, 2161, 2166, 2185, 2191, 2214, 2221, 2248, 2256, 2287, 2296, 2331, 2341, 2380, 2391, 2434, 2446, 2493, 2506, 2557, 2571, 2626, 2641, 2700, 2716, 2779, 2796, 2863, 2881, 2952, 2971, 3046, 3066, 3145, 3166, 3249, 3271, 3358, 3381, 3472, 3496, 4199, 4200, 4203, 4205, 4212, 4215, 4226, 4230, 4245, 4250, 4269, 4275, 4298, 4305, 4332, 4340, 4371, 4380, 4415, 4425, 4464, 4475, 4518, 4530, 4577, 4590, 4641, 4655, 4710, 4725, 4784, 4800, 4863, 4880, 4947, 4965, 5036, 5055, 5130, 5150, 5229, 5250, 5333, 5355, 5442, 5465, 5556, 5580, 6283, 6284, 6703, 6704, 6707, 6708, 6711, 6712);
    model.component("comp1").selection("sel4").set("groupcontang", true);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").set(1, 2, 3, 4, 5, 6, 7, 1259, 1260, 1261, 1262);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"uni1", "sel5"});

    model.component("comp1").physics("acpr").prop("cref").set("cref", "343[m/s]");
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr").feature("sym1").selection().named("sel4");
    model.component("comp1").physics("acpr").create("pom1", "PoroacousticsModel", 3);
    model.component("comp1").physics("acpr").feature("pom1").selection().named("uni1");
    model.component("comp1").physics("acpr").feature("pom1").set("FluidModel", "JohnsonChampouxAllard");
    model.component("comp1").physics("acpr").feature("pom1").set("SolidMaterial", "mat2");
    model.component("comp1").physics("acpr").create("port1", "Port", 2);
    model.component("comp1").physics("acpr").feature("port1").selection().set(1);
    model.component("comp1").physics("acpr").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("acpr").feature("port1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("port1").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("acpr").feature("port1").feature("cpra1").selection().set(1, 3);
    model.component("comp1").physics("acpr").create("port2", "Port", 2);
    model.component("comp1").physics("acpr").feature("port2").selection().set(6714);
    model.component("comp1").physics("acpr").feature("port2").set("PortType", "Circular");
    model.component("comp1").physics("acpr").feature("port2").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("acpr").feature("port2").feature("cpra1").selection().set(6609, 6611);
    model.component("comp1").physics("acpr").create("nra1", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra1").selection().named("dif1");
    model.component("comp1").physics("acpr").feature("nra1").set("DuctType", "RectangularDuct");
    model.component("comp1").physics("acpr").feature("nra1").set("a_rect", "hole_size");
    model.component("comp1").physics("acpr").feature("nra1").set("b_rect", "hole_size");

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection()
         .set(33, 38, 42, 48, 52, 56, 63, 67, 71, 75, 83, 87, 91, 95, 99, 108, 112, 116, 120, 124, 128, 138, 142, 146, 150, 154, 158, 162, 173, 177, 181, 185, 189, 193, 197, 201, 213, 217, 221, 225, 229, 233, 237, 241, 245, 258, 262, 266, 270, 274, 278, 282, 286, 290, 294, 308, 312, 316, 320, 324, 328, 332, 336, 340, 344, 348, 363, 367, 371, 375, 379, 383, 387, 391, 395, 399, 403, 407, 423, 427, 431, 435, 439, 443, 447, 451, 455, 459, 463, 467, 471, 488, 492, 496, 500, 504, 508, 512, 516, 520, 524, 528, 532, 536, 540, 558, 562, 566, 570, 574, 578, 582, 586, 590, 594, 598, 602, 606, 610, 614, 633, 637, 641, 645, 649, 653, 657, 661, 665, 669, 673, 677, 681, 685, 689, 693, 713, 717, 721, 725, 729, 733, 737, 741, 745, 749, 753, 757, 761, 765, 769, 773, 777, 798, 802, 806, 810, 814, 818, 822, 826, 830, 834, 838, 842, 846, 850, 854, 858, 862, 866, 888, 892, 896, 900, 904, 908, 912, 916, 920, 924, 928, 932, 936, 940, 944, 948, 952, 956, 960, 983, 987, 991, 995, 999, 1003, 1007, 1011, 1015, 1019, 1023, 1027, 1031, 1035, 1039, 1043, 1047, 1051, 1055, 1059, 1083, 1087, 1091, 1095, 1099, 1103, 1107, 1111, 1115, 1119, 1123, 1127, 1131, 1135, 1139, 1143, 1147, 1151, 1155, 1159, 1163, 1188, 1192, 1196, 1200, 1204, 1208, 1212, 1216, 1220, 1224, 1228, 1232, 1236, 1240, 1244, 1248, 1252, 1256, 1260, 1264, 1268, 1272, 1298, 1302, 1306, 1310, 1314, 1318, 1322, 1326, 1330, 1334, 1338, 1342, 1346, 1350, 1354, 1358, 1362, 1366, 1370, 1374, 1378, 1382, 1386, 1414, 1418, 1422, 1426, 1430, 1434, 1438, 1442, 1446, 1450, 1454, 1458, 1462, 1466, 1470, 1474, 1478, 1482, 1486, 1490, 1494, 1498, 1524, 1528, 1532, 1536, 1540, 1544, 1548, 1552, 1556, 1560, 1564, 1568, 1572, 1576, 1580, 1584, 1588, 1592, 1596, 1600, 1624, 1628, 1632, 1636, 1640, 1644, 1648, 1652, 1656, 1660, 1664, 1668, 1672, 1676, 1680, 1684, 1688, 1692, 1696, 1719, 1723, 1727, 1731, 1735, 1739, 1743, 1747, 1751, 1755, 1759, 1763, 1767, 1771, 1775, 1779, 1783, 1787, 1809, 1813, 1817, 1821, 1825, 1829, 1833, 1837, 1841, 1845, 1849, 1853, 1857, 1861, 1865, 1869, 1889, 1893, 1897, 1901, 1905, 1909, 1913, 1917, 1921, 1925, 1929, 1933, 1937, 1941, 1945, 1964, 1968, 1972, 1976, 1980, 1984, 1988, 1992, 1996, 2000, 2004, 2008, 2012, 2029, 2033, 2037, 2041, 2045, 2049, 2053, 2057, 2061, 2065, 2079, 2083, 2087, 2091, 2095, 2099, 2103);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lambda_air/6");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "wall_size");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(30);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection()
         .set(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 910, 911, 912, 913, 914, 915, 916, 917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022, 1023, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031, 1032, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048, 1049, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1071, 1072, 1073, 1074, 1075, 1076, 1077, 1078, 1079, 1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087, 1088, 1089, 1090, 1091, 1092, 1093, 1094, 1095, 1096, 1097, 1098, 1099, 1100, 1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118, 1119, 1120, 1121, 1122, 1123, 1124, 1125, 1126, 1127, 1128, 1129, 1130, 1131, 1132, 1133, 1134, 1135, 1136, 1137, 1138, 1139, 1140, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1150, 1151, 1152, 1153, 1154, 1155, 1156, 1157, 1158, 1159, 1160, 1161, 1162, 1163, 1164, 1165, 1166, 1167, 1168, 1169, 1170, 1171, 1172, 1173, 1174, 1175, 1176, 1177, 1178, 1179, 1180, 1181, 1182, 1183, 1184, 1185, 1186, 1187, 1188, 1189, 1190, 1191, 1192, 1193, 1194, 1195, 1196, 1197, 1198, 1199, 1200, 1201, 1202, 1203, 1204, 1205, 1206, 1207, 1208, 1209, 1210, 1211, 1212, 1213, 1214, 1215, 1216, 1217, 1218, 1219, 1220, 1221, 1222, 1223, 1224, 1225, 1226, 1227, 1228, 1229, 1230, 1231, 1232, 1233, 1234, 1235, 1236, 1237, 1238, 1239, 1240, 1241, 1242, 1243, 1244, 1245, 1246, 1247, 1248, 1249, 1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(5, 6, 7, 1259, 1260, 1261);
    model.component("comp1").mesh("mesh1").feature("ftet1").set("optcurved", false);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(1, 3, 4, 1262);
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "range(50,25,1250)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 49, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 49, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 49, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");

    model.result("pg3").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1")
         .set("filename", "diesel_particulate_filter_transfer_matrix_filter_geometry.mphbin");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").selection().create("sel6", "Explicit");
    model.component("comp2").selection("sel6").set(1, 1253);
    model.component("comp2").selection().create("sel7", "Explicit");
    model.component("comp2").selection("sel7").all();
    model.component("comp2").selection().create("sel8", "Explicit");
    model.component("comp2").selection("sel8")
         .set(3, 5, 6, 8, 10, 12, 13, 15, 17, 19, 21, 23, 24, 26, 28, 30, 32, 34, 36, 38, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99, 101, 103, 105, 107, 108, 110, 112, 114, 116, 118, 120, 122, 124, 126, 128, 130, 132, 134, 136, 138, 139, 141, 143, 145, 147, 149, 151, 153, 155, 157, 159, 161, 163, 165, 167, 169, 171, 173, 174, 176, 178, 180, 182, 184, 186, 188, 190, 192, 194, 196, 198, 200, 202, 204, 206, 208, 210, 212, 213, 215, 217, 219, 221, 223, 225, 227, 229, 231, 233, 235, 237, 239, 241, 243, 245, 247, 249, 251, 253, 255, 256, 258, 260, 262, 264, 266, 268, 270, 272, 274, 276, 278, 280, 282, 284, 286, 288, 290, 292, 294, 296, 298, 300, 301, 303, 305, 307, 309, 311, 313, 315, 317, 319, 322, 324, 326, 328, 330, 332, 334, 336, 338, 340, 342, 344, 346, 348, 350, 352, 354, 356, 359, 361, 363, 365, 367, 369, 371, 373, 374, 376, 378, 380, 382, 384, 386, 388, 390, 392, 394, 396, 398, 400, 402, 404, 406, 408, 410, 413, 415, 417, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 838, 841, 843, 845, 848, 850, 852, 854, 856, 859, 861, 863, 865, 867, 869, 871, 874, 876, 878, 880, 882, 884, 886, 888, 890, 893, 895, 897, 899, 901, 903, 905, 907, 909, 911, 913, 916, 918, 920, 922, 924, 926, 928, 930, 932, 934, 936, 938, 940, 943, 945, 947, 949, 951, 953, 955, 957, 959, 961, 963, 965, 967, 969, 971, 974, 976, 978, 980, 982, 984, 986, 988, 990, 992, 994, 996, 998, 1000, 1002, 1004, 1006, 1009, 1011, 1013, 1015, 1017, 1019, 1021, 1023, 1025, 1027, 1029, 1031, 1033, 1035, 1037, 1039, 1041, 1043, 1045, 1048, 1050, 1052, 1054, 1056, 1058, 1060, 1062, 1064, 1066, 1068, 1070, 1072, 1074, 1076, 1078, 1080, 1082, 1084, 1086, 1088, 1091, 1093, 1095, 1097, 1099, 1101, 1103, 1105, 1107, 1109, 1111, 1113, 1115, 1117, 1119, 1121, 1123, 1125, 1127, 1129, 1131, 1133, 1136, 1138, 1140, 1142, 1144, 1146, 1148, 1150, 1152, 1154, 1155, 1157, 1159, 1161, 1163, 1165, 1167, 1169, 1171, 1173, 1175, 1177, 1179, 1181, 1183, 1185, 1187, 1189, 1191, 1192, 1194, 1196, 1198, 1200, 1202, 1204, 1206, 1209, 1211, 1213, 1215, 1217, 1219, 1221, 1223, 1225, 1227, 1229, 1231, 1233, 1235, 1237, 1239, 1241, 1243, 1245, 1246, 1248, 1250, 1252);
    model.component("comp2").selection().create("dif2", "Difference");
    model.component("comp2").selection("dif2").set("add", new String[]{"sel7"});
    model.component("comp2").selection("dif2").set("subtract", new String[]{"sel6", "sel8"});
    model.component("comp2").selection().create("sel9", "Explicit");
    model.component("comp2").selection("sel9").geom(2);
    model.component("comp2").selection("sel9")
         .set(2, 3, 6, 7, 10, 12, 19, 22, 33, 37, 52, 57, 76, 82, 105, 112, 139, 147, 178, 187, 222, 232, 271, 282, 325, 337, 384, 397, 448, 462, 517, 532, 591, 607, 670, 687, 754, 772, 843, 862, 937, 957, 1036, 1057, 1140, 1162, 1249, 1272, 1363, 1387, 2090, 2091, 2094, 2096, 2103, 2106, 2117, 2121, 2136, 2141, 2160, 2166, 2189, 2196, 2223, 2231, 2262, 2271, 2306, 2316, 2355, 2366, 2409, 2421, 2468, 2481, 2532, 2546, 2601, 2616, 2675, 2691, 2754, 2771, 2838, 2856, 2927, 2946, 3021, 3041, 3120, 3141, 3224, 3246, 3333, 3356, 3447, 3471, 4174, 4175, 4178, 4180, 4187, 4190, 4201, 4205, 4220, 4225, 4244, 4250, 4273, 4280, 4307, 4315, 4346, 4355, 4390, 4400, 4439, 4450, 4493, 4505, 4552, 4565, 4616, 4630, 4685, 4700, 4759, 4775, 4838, 4855, 4922, 4940, 5011, 5030, 5105, 5125, 5204, 5225, 5308, 5330, 5417, 5440, 5531, 5555, 6258, 6259);

    model.component("comp2").material().create("mat3", "Common");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat3").label("Air");
    model.component("comp2").material("mat3").set("family", "air");
    model.component("comp2").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat3").materialType("nonSolid");

    model.component("comp2").physics().create("acpr2", "PressureAcoustics", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", true);

    model.component("comp2").physics("acpr2").prop("PortSweepSettings").set("PortSweep", "StandardSweep");
    model.component("comp2").physics("acpr2").prop("cref").set("cref", "343[m/s]");
    model.component("comp2").physics("acpr2").create("port1", "Port", 2);
    model.component("comp2").physics("acpr2").feature("port1").selection().set(1);
    model.component("comp2").physics("acpr2").feature("port1").set("PortType", "Circular");
    model.component("comp2").physics("acpr2").feature("port1").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp2").physics("acpr2").feature("port1").feature("cpra1").selection().set(1, 3);
    model.component("comp2").physics("acpr2").create("port2", "Port", 2);
    model.component("comp2").physics("acpr2").feature("port2").selection().set(6677);
    model.component("comp2").physics("acpr2").feature("port2").set("PortType", "Circular");
    model.component("comp2").physics("acpr2").feature("port2").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp2").physics("acpr2").feature("port2").feature("cpra1").selection().set(6580, 6582);
    model.component("comp2").physics("acpr2").create("pom1", "PoroacousticsModel", 3);
    model.component("comp2").physics("acpr2").feature("pom1").selection().named("dif2");
    model.component("comp2").physics("acpr2").feature("pom1").set("FluidModel", "JohnsonChampouxAllard");
    model.component("comp2").physics("acpr2").feature("pom1").set("SolidMaterial", "mat2");
    model.component("comp2").physics("acpr2").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("acpr2").feature("sym1").selection().named("sel9");
    model.component("comp2").physics("acpr2").create("nra1", "NarrowRegionAcousticsModel", 3);
    model.component("comp2").physics("acpr2").feature("nra1").selection().named("sel8");
    model.component("comp2").physics("acpr2").feature("nra1").set("DuctType", "RectangularDuct");
    model.component("comp2").physics("acpr2").feature("nra1").set("a_rect", "hole_size");
    model.component("comp2").physics("acpr2").feature("nra1").set("b_rect", "hole_size");

    model.component("comp2").mesh("mesh2").create("fq1", "FreeQuad");
    model.component("comp2").mesh("mesh2").feature("fq1").selection()
         .set(8, 13, 17, 23, 27, 31, 38, 42, 46, 50, 58, 62, 66, 70, 74, 83, 87, 91, 95, 99, 103, 113, 117, 121, 125, 129, 133, 137, 148, 152, 156, 160, 164, 168, 172, 176, 188, 192, 196, 200, 204, 208, 212, 216, 220, 233, 237, 241, 245, 249, 253, 257, 261, 265, 269, 283, 287, 291, 295, 299, 303, 307, 311, 315, 319, 323, 338, 342, 346, 350, 354, 358, 362, 366, 370, 374, 378, 382, 398, 402, 406, 410, 414, 418, 422, 426, 430, 434, 438, 442, 446, 463, 467, 471, 475, 479, 483, 487, 491, 495, 499, 503, 507, 511, 515, 533, 537, 541, 545, 549, 553, 557, 561, 565, 569, 573, 577, 581, 585, 589, 608, 612, 616, 620, 624, 628, 632, 636, 640, 644, 648, 652, 656, 660, 664, 668, 688, 692, 696, 700, 704, 708, 712, 716, 720, 724, 728, 732, 736, 740, 744, 748, 752, 773, 777, 781, 785, 789, 793, 797, 801, 805, 809, 813, 817, 821, 825, 829, 833, 837, 841, 863, 867, 871, 875, 879, 883, 887, 891, 895, 899, 903, 907, 911, 915, 919, 923, 927, 931, 935, 958, 962, 966, 970, 974, 978, 982, 986, 990, 994, 998, 1002, 1006, 1010, 1014, 1018, 1022, 1026, 1030, 1034, 1058, 1062, 1066, 1070, 1074, 1078, 1082, 1086, 1090, 1094, 1098, 1102, 1106, 1110, 1114, 1118, 1122, 1126, 1130, 1134, 1138, 1163, 1167, 1171, 1175, 1179, 1183, 1187, 1191, 1195, 1199, 1203, 1207, 1211, 1215, 1219, 1223, 1227, 1231, 1235, 1239, 1243, 1247, 1273, 1277, 1281, 1285, 1289, 1293, 1297, 1301, 1305, 1309, 1313, 1317, 1321, 1325, 1329, 1333, 1337, 1341, 1345, 1349, 1353, 1357, 1361, 1389, 1393, 1397, 1401, 1405, 1409, 1413, 1417, 1421, 1425, 1429, 1433, 1437, 1441, 1445, 1449, 1453, 1457, 1461, 1465, 1469, 1473, 1499, 1503, 1507, 1511, 1515, 1519, 1523, 1527, 1531, 1535, 1539, 1543, 1547, 1551, 1555, 1559, 1563, 1567, 1571, 1575, 1599, 1603, 1607, 1611, 1615, 1619, 1623, 1627, 1631, 1635, 1639, 1643, 1647, 1651, 1655, 1659, 1663, 1667, 1671, 1694, 1698, 1702, 1706, 1710, 1714, 1718, 1722, 1726, 1730, 1734, 1738, 1742, 1746, 1750, 1754, 1758, 1762, 1784, 1788, 1792, 1796, 1800, 1804, 1808, 1812, 1816, 1820, 1824, 1828, 1832, 1836, 1840, 1844, 1864, 1868, 1872, 1876, 1880, 1884, 1888, 1892, 1896, 1900, 1904, 1908, 1912, 1916, 1920, 1939, 1943, 1947, 1951, 1955, 1959, 1963, 1967, 1971, 1975, 1979, 1983, 1987, 2004, 2008, 2012, 2016, 2020, 2024, 2028, 2032, 2036, 2040, 2054, 2058, 2062, 2066, 2070, 2074, 2078);
    model.component("comp2").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "lambda_air/6");
    model.component("comp2").mesh("mesh2").feature("size").set("hmin", "wall_size");
    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().set(5);
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").selection().set(419);
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("numelem", 20);
    model.component("comp2").mesh("mesh2").feature("swe1").create("dis2", "Distribution");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis2").selection().set(1, 1253);
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis2").set("numelem", 2);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "c_air", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m/s", 0);
    model.study("std2").feature("param").setIndex("pname", "c_air", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m/s", 0);
    model.study("std2").feature("param").setIndex("pname", "PortName", 0);
    model.study("std2").feature("param").setIndex("plistarr", "1 2", 0);
    model.study("std2").feature("freq").set("plist", "range(50,50,1250)");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("i1").active(true);

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset5");
    model.result("pg4").setIndex("looplevel", 25, 0);
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr2.p_t"});
    model.result("pg4").feature("surf1").set("colortable", "Wave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b (acpr2)");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset5");
    model.result("pg5").setIndex("looplevel", 25, 0);
    model.result("pg5").setIndex("looplevel", 2, 1);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"acpr2.Lp_t"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").label("\u58f0\u538b\u7ea7 (acpr2)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").setIndex("looplevel", 25, 0);
    model.result("pg6").setIndex("looplevel", 2, 1);
    model.result("pg6").create("iso1", "Isosurface");
    model.result("pg6").feature("iso1").set("expr", new String[]{"acpr2.p_t"});
    model.result("pg6").feature("iso1").set("number", "10");
    model.result("pg6").feature("iso1").set("colortable", "Wave");
    model.result("pg6").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr2)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg4").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");

    model.result("pg6").run();
    model.result().dataset().remove("dset2");
    model.result().dataset().remove("dset4");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset5");
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "freq", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Frequency", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "real(acpr2.T11)", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "imag(acpr2.T11)", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "real(acpr2.T12)", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "imag(acpr2.T12)", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "real(acpr2.T21)", 5);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "imag(acpr2.T21)", 6);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "real(acpr2.T22)", 7);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "imag(acpr2.T22)", 8);
    model.result().evaluationGroup("eg1").run();

    model.component().create("comp3", true);

    model.component("comp3").geom().create("geom3", 3);
    model.component("comp3").geom("geom3").geomRep("comsol");

    model.component("comp3").mesh().create("mesh3");
    model.component("comp3").mesh("mesh3").contribute("geom/detail", true);

    model.component("comp3").geom("geom3").lengthUnit("mm");
    model.component("comp3").geom("geom3").create("imp1", "Import");
    model.component("comp3").geom("geom3").feature("imp1")
         .set("filename", "diesel_particulate_filter_transfer_matrix_lumped_model_geometry.mphbin");
    model.component("comp3").geom("geom3").feature("imp1").importData();
    model.component("comp3").geom("geom3").run("fin");

    model.component("comp3").variable().create("var1");

//    To import content from file, use:
//    model.component("comp3").variable("var1").loadFile("FILENAME");
    model.component("comp3").variable("var1")
         .set("T11", "T11real(freq)+i*T11imag(freq)", "\u8f6c\u79fb\u77e9\u9635\uff0c11 \u5143\u7d20");
    model.component("comp3").variable("var1")
         .set("T12", "T12real(freq)+i*T12imag(freq)", "\u8f6c\u79fb\u77e9\u9635\uff0c12 \u5143\u7d20");
    model.component("comp3").variable("var1")
         .set("T21", "T21real(freq)+i*T21imag(freq)", "\u8f6c\u79fb\u77e9\u9635\uff0c21 \u5143\u7d20");
    model.component("comp3").variable("var1")
         .set("T22", "T22real(freq)+i*T22imag(freq)", "\u8f6c\u79fb\u77e9\u9635\uff0c22 \u5143\u7d20");

    model.component("comp3").func().create("int1", "Interpolation");
    model.component("comp3").func("int1").set("source", "resultTable");
    model.component("comp3").func("int1").setEntry("columnType", "col3", "value");
    model.component("comp3").func("int1").setEntry("columnType", "col4", "value");
    model.component("comp3").func("int1").setEntry("columnType", "col5", "value");
    model.component("comp3").func("int1").setEntry("columnType", "col6", "value");
    model.component("comp3").func("int1").setEntry("columnType", "col7", "value");
    model.component("comp3").func("int1").setEntry("columnType", "col8", "value");
    model.component("comp3").func("int1").setEntry("columnType", "col9", "value");
    model.component("comp3").func("int1").setIndex("argunit", "Hz", 0);
    model.component("comp3").func("int1").setEntry("funcnames", "col2", "T11real");
    model.component("comp3").func("int1").setIndex("fununit", "1", 0);

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp3").func("int1").setEntry("funcnames", "col3", "T11imag");
    model.component("comp3").func("int1").setIndex("fununit", "1", 1);
    model.component("comp3").func("int1").setEntry("funcnames", "col4", "T12real");
    model.component("comp3").func("int1").setIndex("fununit", "kg/(m^4*s)", 2);
    model.component("comp3").func("int1").setEntry("funcnames", "col5", "T12imag");
    model.component("comp3").func("int1").setIndex("fununit", "kg/(m^4*s)", 3);
    model.component("comp3").func("int1").setEntry("funcnames", "col6", "T21real");
    model.component("comp3").func("int1").setIndex("fununit", "(m^4*s)/kg", 4);
    model.component("comp3").func("int1").setEntry("funcnames", "col7", "T21imag");
    model.component("comp3").func("int1").setIndex("fununit", "(m^4*s)/kg", 5);
    model.component("comp3").func("int1").setEntry("funcnames", "col8", "T22real");
    model.component("comp3").func("int1").setIndex("fununit", "1", 6);
    model.component("comp3").func("int1").setEntry("funcnames", "col9", "T22imag");
    model.component("comp3").func("int1").setIndex("fununit", "1", 7);
    model.component("comp3").func("int1").set("interp", "cubicspline");

    model.component("comp3").selection().create("sel10", "Explicit");
    model.component("comp3").selection("sel10").geom(2);
    model.component("comp3").selection("sel10")
         .set(2, 3, 6, 7, 10, 11, 14, 15, 18, 19, 22, 23, 28, 29, 32, 33, 36, 37);
    model.component("comp3").selection("sel10").set("groupcontang", true);

    model.component("comp3").material().create("mat4", "Common");
    model.component("comp3").material("mat4").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp3").material("mat4").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp3").material("mat4").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp3").material("mat4").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp3").material("mat4").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp3").material("mat4").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp3").material("mat4").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp3").material("mat4").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp3").material("mat4").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp3").material("mat4").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp3").material("mat4").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp3").material("mat4").label("Air");
    model.component("comp3").material("mat4").set("family", "air");
    model.component("comp3").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp3").material("mat4").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp3").material("mat4").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp3").material("mat4").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp3").material("mat4").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp3").material("mat4").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp3").material("mat4").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp3").material("mat4").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp3").material("mat4").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp3").material("mat4").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp3").material("mat4").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp3").material("mat4").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp3").material("mat4").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp3").material("mat4").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp3").material("mat4").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp3").material("mat4").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp3").material("mat4").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp3").material("mat4").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp3").material("mat4").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp3").material("mat4").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp3").material("mat4").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp3").material("mat4").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp3").material("mat4").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp3").material("mat4").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp3").material("mat4").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp3").material("mat4").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp3").material("mat4").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp3").material("mat4").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp3").material("mat4").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp3").material("mat4").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp3").material("mat4").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp3").material("mat4").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp3").material("mat4").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp3").material("mat4").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp3").material("mat4").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp3").material("mat4").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp3").material("mat4").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp3").material("mat4").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp3").material("mat4").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp3").material("mat4").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp3").material("mat4").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp3").material("mat4").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp3").material("mat4").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp3").material("mat4").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp3").material("mat4").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp3").material("mat4").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp3").material("mat4").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp3").material("mat4").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp3").material("mat4").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp3").material("mat4").propertyGroup("def").set("molarmass", "");
    model.component("comp3").material("mat4").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp3").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp3").material("mat4").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp3").material("mat4").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp3").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp3").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp3").material("mat4").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp3").material("mat4").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp3").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp3").material("mat4").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp3").material("mat4").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp3").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp3").material("mat4").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp3").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp3").material("mat4").propertyGroup("def").addInput("pressure");
    model.component("comp3").material("mat4").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp3").material("mat4").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp3").material("mat4").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp3").material("mat4").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp3").material("mat4").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp3").material("mat4").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp3").material("mat4").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp3").material("mat4").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp3").material("mat4").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp3").material("mat4").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp3").material("mat4").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp3").material("mat4").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp3").material("mat4").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp3").material("mat4").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp3").material("mat4").propertyGroup("idealGas").addInput("temperature");
    model.component("comp3").material("mat4").propertyGroup("idealGas").addInput("pressure");
    model.component("comp3").material("mat4").materialType("nonSolid");

    model.component("comp3").physics().create("acpr3", "PressureAcoustics", "geom3");

    model.study("std1").feature("freq").setSolveFor("/physics/acpr3", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr3", true);

    model.component("comp3").physics("acpr3").prop("cref").set("cref", "343[m/s]");
    model.component("comp3").physics("acpr3").create("port1", "Port", 2);
    model.component("comp3").physics("acpr3").feature("port1").selection().set(1);
    model.component("comp3").physics("acpr3").feature("port1").set("PortType", "Circular");
    model.component("comp3").physics("acpr3").feature("port1").set("pamp", 1);
    model.component("comp3").physics("acpr3").feature("port1").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp3").physics("acpr3").feature("port1").feature("cpra1").selection().set(1, 3);
    model.component("comp3").physics("acpr3").create("port2", "Port", 2);
    model.component("comp3").physics("acpr3").feature("port2").selection().set(39);
    model.component("comp3").physics("acpr3").feature("port2").set("PortType", "Circular");
    model.component("comp3").physics("acpr3").feature("port2").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp3").physics("acpr3").feature("port2").feature("cpra1").selection().set(33, 35);
    model.component("comp3").physics("acpr3").create("tmc1", "TransferMatrixCoupling", 2);
    model.component("comp3").physics("acpr3").feature("tmc1").selection().set(26, 27);
    model.component("comp3").physics("acpr3").feature("tmc1").set("Representation", "TransferMatrixLumped");
    model.component("comp3").physics("acpr3").feature("tmc1").set("T11_l", "T11");
    model.component("comp3").physics("acpr3").feature("tmc1").set("T12_l", "T12");
    model.component("comp3").physics("acpr3").feature("tmc1").set("T21_l", "T21");
    model.component("comp3").physics("acpr3").feature("tmc1").set("T22_l", "T22");
    model.component("comp3").physics("acpr3").create("sym1", "Symmetry", 2);
    model.component("comp3").physics("acpr3").feature("sym1").selection().named("sel10");

    model.component("comp3").mesh("mesh3").create("ftri1", "FreeTri");
    model.component("comp3").mesh("mesh3").feature("ftri1").selection().set(21, 24, 27);
    model.component("comp3").mesh("mesh3").feature("ftri1").create("size1", "Size");
    model.component("comp3").mesh("mesh3").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp3").mesh("mesh3").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp3").mesh("mesh3").feature("ftri1").feature("size1").set("hmax", 15);
    model.component("comp3").mesh("mesh3").feature("size").set("custom", true);
    model.component("comp3").mesh("mesh3").feature("size").set("hmax", "lambda_air/6");
    model.component("comp3").mesh("mesh3").feature("size").set("hmin", "hole_size");
    model.component("comp3").mesh("mesh3").create("swe1", "Sweep");
    model.component("comp3").mesh("mesh3").feature("swe1").selection().geom("geom3", 3);
    model.component("comp3").mesh("mesh3").feature("swe1").selection().set(3, 4, 5, 6, 7);
    model.component("comp3").mesh("mesh3").create("ftet1", "FreeTet");
    model.component("comp3").mesh("mesh3").feature("ftet1").selection().geom("geom3", 3);
    model.component("comp3").mesh("mesh3").feature("ftet1").selection().set(2, 8);
    model.component("comp3").mesh("mesh3").create("swe2", "Sweep");
    model.component("comp3").mesh("mesh3").feature("swe2").create("dis1", "Distribution");
    model.component("comp3").mesh("mesh3").feature("swe2").feature("dis1").selection().set(1, 9);
    model.component("comp3").mesh("mesh3").feature("swe2").feature("dis1").set("numelem", 3);
    model.component("comp3").mesh("mesh3").run();

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr3", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr3", false);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr3", false);
    model.study("std3").feature("freq").set("plist", "range(50,2.5,1250)");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std3").showAutoSequences("all");
    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset8");
    model.result("pg7").setIndex("looplevel", 481, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"acpr3.p_t"});
    model.result("pg7").feature("surf1").set("colortable", "Wave");
    model.result("pg7").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").label("\u58f0\u538b (acpr3)");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset8");
    model.result("pg8").setIndex("looplevel", 481, 0);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"acpr3.Lp_t"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").label("\u58f0\u538b\u7ea7 (acpr3)");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset8");
    model.result("pg9").setIndex("looplevel", 481, 0);
    model.result("pg9").create("iso1", "Isosurface");
    model.result("pg9").feature("iso1").set("expr", new String[]{"acpr3.p_t"});
    model.result("pg9").feature("iso1").set("number", "10");
    model.result("pg9").feature("iso1").set("colortable", "Wave");
    model.result("pg9").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr3)");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg7").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").add("plotgroup", "pg9");

    model.result().dataset().remove("dset6");
    model.result().dataset().remove("dset7");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("axislimits", true);
    model.result("pg10").set("xmin", 30);
    model.result("pg10").set("xmax", 1270);
    model.result("pg10").set("ymin", 0);
    model.result("pg10").set("ymax", 15);
    model.result("pg10").create("oct1", "OctaveBand");
    model.result("pg10").feature("oct1").set("quantity", "bandpower");
    model.result("pg10").feature("oct1").set("markerpos", "datapoints");
    model.result("pg10").feature("oct1").set("linewidth", "preference");
    model.result("pg10").feature("oct1").selection().geom("geom1");
    model.result("pg10").feature("oct1").set("expr", "acpr.port1.P_in/acpr.port2.P_out");
    model.result("pg10").feature("oct1").set("descractive", true);
    model.result("pg10").feature("oct1").set("exprtype", "transfer");
    model.result("pg10").feature("oct1").set("quantity", "continuous");
    model.result("pg10").feature("oct1").set("linestyle", "none");
    model.result("pg10").feature("oct1").set("linemarker", "circle");
    model.result("pg10").feature("oct1").set("legend", true);
    model.result("pg10").run();
    model.result("pg10").create("oct2", "OctaveBand");
    model.result("pg10").feature("oct2").set("quantity", "bandpower");
    model.result("pg10").feature("oct2").set("markerpos", "datapoints");
    model.result("pg10").feature("oct2").set("linewidth", "preference");
    model.result("pg10").feature("oct2").set("data", "dset8");
    model.result("pg10").feature("oct2").set("expr", "acpr3.port1.P_in/acpr3.port2.P_out");
    model.result("pg10").feature("oct2").set("descractive", true);
    model.result("pg10").feature("oct2").set("exprtype", "transfer");
    model.result("pg10").feature("oct2").set("quantity", "continuous");
    model.result("pg10").feature("oct2").set("linewidth", 2);
    model.result("pg10").feature("oct2").set("legend", true);
    model.result("pg10").run();
    model.result("pg10").set("xlog", false);
    model.result("pg10").set("xmin", 30);
    model.result("pg10").set("xmax", 1270);
    model.result("pg10").set("ymin", 0);
    model.result("pg10").set("ymax", 15);
    model.result("pg10").set("legendpos", "lowerright");
    model.result("pg10").run();
    model.result().setOnlyPlotWhenRequested(true);
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "none");
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").feature().copy("surf1", "pg2/surf1");
    model.result("pg11").feature("surf1").set("data", "dset1");
    model.result("pg11").feature().duplicate("surf2", "surf1");
    model.result("pg11").feature("surf2").set("data", "dset5");
    model.result("pg11").feature("surf2").set("expr", "acpr2.Lp_t");
    model.result("pg11").feature("surf2").set("inheritplot", "surf1");
    model.result("pg11").feature("surf2").create("trn1", "Transformation");
    model.result("pg11").feature("surf2").feature("trn1").set("move", new int[]{0, -20, -100});
    model.result("pg11").feature().duplicate("surf3", "surf2");
    model.result("pg11").feature("surf3").set("data", "dset8");
    model.result("pg11").feature("surf3").set("expr", "acpr3.Lp_t");
    model.result("pg11").feature("surf3").feature("trn1").set("move", new int[]{0, -40, -200});
    model.result("pg11").run();
    model.result().setOnlyPlotWhenRequested(false);

    model.title("\u4f7f\u7528\u58f0\u8f6c\u79fb\u77e9\u9635\u5206\u6790\u67f4\u6cb9\u9897\u7c92\u8fc7\u6ee4\u5668");

    model
         .description("\u67f4\u6cb9\u9897\u7c92\u8fc7\u6ee4\u5668 (DPF) \u7528\u4e8e\u53bb\u9664\u548c\u8fc7\u6ee4\u67f4\u6cb9\u53d1\u52a8\u673a\u8f66\u8f86\u6392\u653e\u7684\u70df\u5c18\uff08\u67f4\u6cb9\u9897\u7c92\uff09\u3002\u6b64\u7c7b\u7cfb\u7edf\u4e2d\u7684\u8fc7\u6ee4\u5668\u901a\u5e38\u91c7\u7528\u5145\u6ee1\u7a7a\u6c14\u7684\u957f\u901a\u9053\u7ed3\u6784\uff0c\u5176\u4e2d\u5145\u6ee1\u7528\u4e8e\u4fdd\u7559\u70df\u7070\u7684\u591a\u5b54\u4ecb\u8d28\u4e2d\u3002\n\n\u867d\u7136 DPF \u7684\u4e3b\u8981\u529f\u80fd\u662f\u8fc7\u6ee4\u6392\u6c14\u6d41\u4e2d\u7684\u70df\u5c18\uff0c\u4f46\u5b83\u8fd8\u5177\u6709\u4e0e\u6d88\u58f0\u5668\u7cfb\u7edf\u76f8\u5173\u7684\u58f0\u963b\u5c3c\u5c5e\u6027\u3002\u7531\u4e8e\u8fc7\u6ee4\u5668\u901a\u5e38\u5305\u542b\u6570\u5343\u4e2a\u7a7a\u6c14\u901a\u9053\uff0c\u5bf9\u6bcf\u4e2a\u901a\u9053\u8fdb\u884c\u5355\u72ec\u5efa\u6a21\u7684\u8ba1\u7b97\u6210\u672c\u975e\u5e38\u9ad8\u3002\n\n\u672c\u6a21\u578b\u6a21\u62df\u4e86 DPF \u4e2d\u7684\u58f0\u5b66\u6548\u5e94\uff0c\u5e76\u63d0\u51fa\u4e86\u4e00\u79cd\u901a\u8fc7\u83b7\u53d6\u8fc7\u6ee4\u5668\u672c\u8eab\u7684\u8f6c\u79fb\u77e9\u9635\u6765\u964d\u4f4e\u8ba1\u7b97\u590d\u6742\u5ea6\u7684\u65b9\u6cd5\uff0c\u5c06\u8f6c\u79fb\u77e9\u9635\u8026\u5408\u5230\u5468\u56f4\u7684\u8154\u4f53\u4ee5\u83b7\u5f97\u603b\u4f20\u8f93\u635f\u8017 (TL)\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("diesel_particulate_filter_transfer_matrix.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
