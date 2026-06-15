/*
 * seismic_waves_earth.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class seismic_waves_earth {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Elastic_Waves");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("pate", "PressureAcousticsTimeExplicit", "geom1");
    model.component("comp1").physics().create("elte", "ElasticWavesTimeExplicit", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/pate", true);
    model.study("std1").feature("time").setSolveFor("/physics/elte", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "0.1[Hz]", "\u6e90\u9891\u7387");
    model.param().set("T0", "1/f0", "\u6e90\u5468\u671f");
    model.param().set("dS", "50[km^2]", "\u6e90\u5927\u5c0f");
    model.param().set("r0", "0[km]", "\u6e90\u4f4d\u7f6e r \u5750\u6807");
    model.param().set("z0", "6320[km]", "\u6e90\u4f4d\u7f6e z \u5750\u6807");
    model.param().set("phi0", "27[deg]", "\u63a2\u9488\u4f4d\u7f6e");
    model.param().set("r_earth", "6370[km]", "\u5730\u7403\u534a\u5f84");
    model.param().set("th1", "20[km]", "\u7b2c\u4e00\u5c42\u539a\u5ea6");
    model.param().set("th2", "15[km]", "\u7b2c\u4e8c\u5c42\u539a\u5ea6");
    model.param().set("th3", "375[km]", "\u7b2c\u4e09\u5c42\u539a\u5ea6");
    model.param().set("th4", "250[km]", "\u7b2c\u56db\u5c42\u539a\u5ea6");
    model.param().set("th5", "2231[km]", "\u7b2c\u4e94\u5c42\u539a\u5ea6");
    model.param().set("th6", "2244[km]", "\u7b2c\u516d\u5c42\u539a\u5ea6");
    model.param().set("th7", "1160[km]", "\u7b2c\u4e03\u5c42\u539a\u5ea6");
    model.param().set("rho1", "2449[kg/m^3]", "\u7b2c\u4e00\u5c42\u5bc6\u5ea6");
    model.param().set("cp1", "5800[m/s]", "\u7b2c\u4e00\u5c42\u538b\u529b\u6ce2\u7684\u901f\u5ea6");
    model.param().set("cs1", "3460[m/s]", "\u7b2c\u4e00\u5c42\u526a\u5207\u6ce2\u7684\u901f\u5ea6");
    model.param().set("rho2", "2714.2[kg/m^3]", "\u7b2c\u4e8c\u5c42\u5bc6\u5ea6");
    model.param().set("cp2", "6500[m/s]", "\u7b2c\u4e8c\u5c42\u538b\u529b\u6ce2\u7684\u901f\u5ea6");
    model.param().set("cs2", "3850[m/s]", "\u7b2c\u4e8c\u5c42\u526a\u5207\u6ce2\u7684\u901f\u5ea6");
    model.param().set("rho8", "13012[kg/m^3]", "\u7b2c\u516b\u5c42\u5bc6\u5ea6");
    model.param().set("cp8", "11262[m/s]", "\u7b2c\u516b\u5c42\u538b\u529b\u6ce2\u7684\u901f\u5ea6");
    model.param().set("cs8", "3667.6[m/s]", "\u7b2c\u516b\u5c42\u526a\u5207\u6ce2\u7684\u901f\u5ea6");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "G_space");
    model.func("an1").set("expr", "10e26/sqrt(pi*dS)*exp(-((r - r0)^2 + (z - z0)^2)/dS)");
    model.func("an1").set("args", "r,z");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "1");
    model.func("an1").setIndex("plotargs", "100000[m]", 0, 2);
    model.func("an1").setIndex("plotargs", "z0-50000[m]", 1, 1);
    model.func("an1").setIndex("plotargs", "z0+50000[m]", 1, 2);
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "G_time");
    model.func("an2").set("expr", "if(t<2.5*T0,sin(2*pi*f0*t)*sin(2*pi*f0*t/5),0)");
    model.func("an2").set("args", "t");
    model.func("an2").setIndex("argunit", "s", 0);
    model.func("an2").set("fununit", "N");
    model.func("an2").setIndex("plotargs", "10*T0", 0, 2);
    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "rho3");
    model.func("int1")
         .set("table", new String[][]{{"35", "3297.6"}, 
         {"77.5", "3299.4"}, 
         {"120", "3301.3"}, 
         {"165", "3348.7"}, 
         {"210", "3396"}, 
         {"260", "3465.2"}, 
         {"310", "3534.3"}, 
         {"360", "3603.4"}, 
         {"410", "3672.6"}});
    model.func("int1").set("extrap", "linear");
    model.func("int1").setIndex("argunit", "km", 0);
    model.func("int1").setIndex("fununit", "kg/m^3", 0);
    model.func().duplicate("int2", "int1");
    model.func("int2").set("funcname", "cp3");
    model.func("int2")
         .set("table", new String[][]{{"35", "8040"}, 
         {"77.5", "8045"}, 
         {"120", "8050"}, 
         {"165", "8175"}, 
         {"210", "8300"}, 
         {"260", "8482.5"}, 
         {"310", "8665"}, 
         {"360", "8847.5"}, 
         {"410", "9030"}});
    model.func("int2").setIndex("fununit", "m/s", 0);
    model.func().duplicate("int3", "int2");
    model.func("int3").set("funcname", "cs3");
    model.func("int3")
         .set("table", new String[][]{{"35", "4480"}, 
         {"77.5", "4490"}, 
         {"120", "4500"}, 
         {"165", "4509"}, 
         {"210", "4518"}, 
         {"260", "4609"}, 
         {"310", "4696"}, 
         {"360", "4783"}, 
         {"410", "4870"}});
    model.func().duplicate("int4", "int3");
    model.func("int4").set("funcname", "rho4");
    model.func("int4")
         .set("table", new String[][]{{"410", "3797.6"}, 
         {"460", "3861.2"}, 
         {"510", "3924.8"}, 
         {"560", "3988.5"}, 
         {"610", "4052.1"}, 
         {"660", "4115.8"}});
    model.func("int4").setIndex("fununit", "kg/m^3", 0);
    model.func().duplicate("int5", "int4");
    model.func("int5").set("funcname", "cp4");
    model.func("int5")
         .set("table", new String[][]{{"410", "9360"}, 
         {"460", "9528"}, 
         {"510", "9696"}, 
         {"560", "9864"}, 
         {"610", "10032"}, 
         {"660", "10200"}});
    model.func("int5").setIndex("fununit", "m/s", 0);
    model.func().duplicate("int6", "int5");
    model.func("int6").set("funcname", "cs4");
    model.func("int6")
         .set("table", new String[][]{{"410", "5080"}, 
         {"460", "5186"}, 
         {"510", "5292"}, 
         {"560", "5398"}, 
         {"610", "5504"}, 
         {"660", "5610"}});
    model.func("int6").setIndex("fununit", "m/s", 0);
    model.func().duplicate("int7", "int6");
    model.func("int7").set("funcname", "rho5");
    model.func("int7")
         .set("table", new String[][]{{"660", "4339.3"}, 
         {"710", "4389.6"}, 
         {"760", "4439.9"}, 
         {"809.5", "4470.1"}, 
         {"859", "4502.9"}, 
         {"908.5", "4535"}, 
         {"958", "4566.4"}, 
         {"1007.5", "4597"}, 
         {"1057", "4627"}, 
         {"1106.5", "4656.3"}, 
         {"1156", "4684.9"}, 
         {"1205.5", "4713"}, 
         {"1255", "4740.4"}, 
         {"1304.5", "4767.3"}, 
         {"1354", "4793.6"}, 
         {"1403.5", "4819.5"}, 
         {"1453", "4844.8"}, 
         {"1502.5", "4870"}, 
         {"1552", "4894.2"}, 
         {"1601.5", "4918.2"}, 
         {"1651", "4942.3"}, 
         {"1700.5", "4965.3"}, 
         {"1750", "4988.2"}, 
         {"1799.5", "5010.9"}, 
         {"1849", "5033.3"}, 
         {"1898.5", "5055.3"}, 
         {"1948", "5077.2"}, 
         {"1997.5", "5099"}, 
         {"2047", "5120.6"}, 
         {"2096.5", "5142.2"}, 
         {"2146", "5163.8"}, 
         {"2195.5", "5184.8"}, 
         {"2245", "5206.1"}, 
         {"2294.5", "5227"}, 
         {"2344", "5248.1"}, 
         {"2393.5", "5269.8"}, 
         {"2443", "5290.7"}, 
         {"2492.5", "5312.2"}, 
         {"2542", "5333.8"}, 
         {"2591.5", "5356"}, 
         {"2640", "5377.6"}, 
         {"2690", "5399.9"}, 
         {"2740", "5422.4"}, 
         {"2789.67", "5423.8"}, 
         {"2839.33", "5425.1"}, 
         {"2891.5", "5426.5"}});
    model.func("int7").setIndex("fununit", "kg/m^3", 0);
    model.func().duplicate("int8", "int7");
    model.func("int8").set("funcname", "cp5");
    model.func("int8")
         .set("table", new String[][]{{"660", "10790"}, 
         {"710", "10922.9"}, 
         {"760", "11055.8"}, 
         {"809.5", "11135.3"}, 
         {"859", "11222.1"}, 
         {"908.5", "11306.8"}, 
         {"958", "11389.6"}, 
         {"1007.5", "11470.5"}, 
         {"1057", "11549.5"}, 
         {"1106.5", "11626.9"}, 
         {"1156", "11702.6"}, 
         {"1205.5", "11776.6"}, 
         {"1255", "11849.1"}, 
         {"1304.5", "11920"}, 
         {"1354", "11989.5"}, 
         {"1403.5", "12057.7"}, 
         {"1453", "12124.5"}, 
         {"1502.5", "12191.2"}, 
         {"1552", "12255"}, 
         {"1601.5", "12318.5"}, 
         {"1651", "12381.9"}, 
         {"1700.5", "12442.6"}, 
         {"1750", "12503.1"}, 
         {"1799.5", "12563.1"}, 
         {"1849", "12622.1"}, 
         {"1898.5", "12680.4"}, 
         {"1948", "12738.2"}, 
         {"1997.5", "12795.6"}, 
         {"2047", "12852.6"}, 
         {"2096.5", "12909.6"}, 
         {"2146", "12966.8"}, 
         {"2195.5", "13022.2"}, 
         {"2245", "13078.3"}, 
         {"2294.5", "13133.6"}, 
         {"2344", "13189.4"}, 
         {"2393.5", "13246.5"}, 
         {"2443", "13301.8"}, 
         {"2492.5", "13358.5"}, 
         {"2542", "13415.6"}, 
         {"2591.5", "13474.1"}, 
         {"2640", "13531.2"}, 
         {"2690", "13590"}, 
         {"2740", "13649.4"}, 
         {"2789.67", "13653"}, 
         {"2839.33", "13656.6"}, 
         {"2891.5", "13660.2"}});
    model.func("int8").setIndex("fununit", "m/s", 0);
    model.func().duplicate("int9", "int8");
    model.func("int9").set("funcname", "cs5");
    model.func("int9")
         .set("table", new String[][]{{"660", "5960"}, 
         {"710", "6089.7"}, 
         {"760", "6209.5"}, 
         {"809.5", "6242.6"}, 
         {"859", "6279.8"}, 
         {"908.5", "6316"}, 
         {"958", "6351.2"}, 
         {"1007.5", "6385.4"}, 
         {"1057", "6418.7"}, 
         {"1106.5", "6451"}, 
         {"1156", "6482.8"}, 
         {"1205.5", "6513.8"}, 
         {"1255", "6543.9"}, 
         {"1304.5", "6572.7"}, 
         {"1354", "6600.8"}, 
         {"1403.5", "6628.5"}, 
         {"1453", "6655.5"}, 
         {"1502.5", "6681.5"}, 
         {"1552", "6707.3"}, 
         {"1601.5", "6732.6"}, 
         {"1651", "6757.3"}, 
         {"1700.5", "6781.5"}, 
         {"1750", "6805.2"}, 
         {"1799.5", "6828.6"}, 
         {"1849", "6851.5"}, 
         {"1898.5", "6874.2"}, 
         {"1948", "6897.2"}, 
         {"1997.5", "6919.4"}, 
         {"2047", "6941.8"}, 
         {"2096.5", "6962.7"}, 
         {"2146", "6985.5"}, 
         {"2195.5", "7006.3"}, 
         {"2245", "7028.1"}, 
         {"2294.5", "7050"}, 
         {"2344", "7072"}, 
         {"2393.5", "7093.1"}, 
         {"2443", "7114.4"}, 
         {"2492.5", "7136.9"}, 
         {"2542", "7158.6"}, 
         {"2591.5", "7180.7"}, 
         {"2640", "7203.1"}, 
         {"2690", "7225.8"}, 
         {"2740", "7249"}, 
         {"2789.67", "7259.7"}, 
         {"2839.33", "7270.4"}, 
         {"2891.5", "7281.1"}});
    model.func("int9").setIndex("fununit", "m/s", 0);
    model.func().duplicate("int10", "int9");
    model.func("int10").set("funcname", "rho6");
    model.func("int10")
         .set("table", new String[][]{{"2891.5", "9914.5"}, 
         {"2939.33", "9994.2"}, 
         {"2989.66", "10072.2"}, 
         {"3039.99", "10148.5"}, 
         {"3090.32", "10223.3"}, 
         {"3140.66", "10296.4"}, 
         {"3190.99", "10367.9"}, 
         {"3241.32", "10437.8"}, 
         {"3291.65", "10506.2"}, 
         {"3341.98", "10573.1"}, 
         {"3392.31", "10638.5"}, 
         {"3442.64", "10702.3"}, 
         {"3492.97", "10764.7"}, 
         {"3543.3", "10825.7"}, 
         {"3593.64", "10885.2"}, 
         {"3643.97", "10943.4"}, 
         {"3694.3", "11000.1"}, 
         {"3744.63", "11055.5"}, 
         {"3794.96", "11109.5"}, 
         {"3845.29", "11162.3"}, 
         {"3895.62", "11213.7"}, 
         {"3945.95", "11263.9"}, 
         {"3996.28", "11312.7"}, 
         {"4046.62", "11360.4"}, 
         {"4096.95", "11406.9"}, 
         {"4147.28", "11452.1"}, 
         {"4197.61", "11496.2"}, 
         {"4247.94", "11539.1"}, 
         {"4298.27", "11580.9"}, 
         {"4348.6", "11621.6"}, 
         {"4398.93", "11661.2"}, 
         {"4449.26", "11699.8"}, 
         {"4499.6", "11737.3"}, 
         {"4549.93", "11773.7"}, 
         {"4600.26", "11809.2"}, 
         {"4650.59", "11843.7"}, 
         {"4700.92", "11877.2"}, 
         {"4801.58", "11941.4"}, 
         {"4851.91", "11972.2"}, 
         {"4902.24", "12000.1"}, 
         {"4952.58", "12031.1"}, 
         {"5002.91", "12059.3"}, 
         {"5053.24", "12086.7"}, 
         {"5103.57", "12113.3"}, 
         {"5153.5", "12139.1"}});
    model.func("int10").setIndex("fununit", "kg/m^3", 0);
    model.func().duplicate("int11", "int10");
    model.func("int11").set("funcname", "cp6");
    model.func("int11")
         .set("table", new String[][]{{"2891.5", "8000"}, 
         {"2939.33", "8038.2"}, 
         {"2989.66", "8128.3"}, 
         {"3039.99", "8221.3"}, 
         {"3090.32", "8312.2"}, 
         {"3140.66", "8400.1"}, 
         {"3190.99", "8486.1"}, 
         {"3241.32", "8569.2"}, 
         {"3291.65", "8649.6"}, 
         {"3341.98", "8728.3"}, 
         {"3392.31", "8803.6"}, 
         {"3442.64", "8876.1"}, 
         {"3492.97", "8946.1"}, 
         {"3543.3", "9013.8"}, 
         {"3593.64", "9079.2"}, 
         {"3643.97", "9142.6"}, 
         {"3694.3", "9204.2"}, 
         {"3744.63", "9263.4"}, 
         {"3794.96", "9320.5"}, 
         {"3845.29", "9376"}, 
         {"3895.62", "9429.7"}, 
         {"3945.95", "9481.4"}, 
         {"3996.28", "9530.6"}, 
         {"4046.62", "9577.7"}, 
         {"4096.95", "9623.2"}, 
         {"4147.28", "9667.3"}, 
         {"4197.61", "9710"}, 
         {"4247.94", "9751.3"}, 
         {"4298.27", "9791.4"}, 
         {"4348.6", "9830.4"}, 
         {"4398.93", "9868.2"}, 
         {"4449.26", "9905.1"}, 
         {"4499.6", "9941"}, 
         {"4549.93", "9976.1"}, 
         {"4600.26", "10010.3"}, 
         {"4650.59", "10043.9"}, 
         {"4700.92", "10076.8"}, 
         {"4801.58", "10141.5"}, 
         {"4851.91", "10173.9"}, 
         {"4902.24", "10204.9"}, 
         {"4952.58", "10232.9"}, 
         {"5002.91", "10256.5"}, 
         {"5053.24", "10274.5"}, 
         {"5103.57", "10285.4"}, 
         {"5153.5", "10289"}});
    model.func("int11").setIndex("fununit", "m/s", 0);
    model.func().duplicate("int12", "int11");
    model.func("int12").set("funcname", "rho7");
    model.func("int12")
         .set("table", new String[][]{{"5153.5", "12703.7"}, 
         {"5204.61", "12728.9"}, 
         {"5255.32", "12753"}, 
         {"5306.04", "12776"}, 
         {"5356.75", "12798"}, 
         {"5407.46", "12818.8"}, 
         {"5458.17", "12838.7"}, 
         {"5508.89", "12857.4"}, 
         {"5559.6", "12875.1"}, 
         {"5610.31", "12891.7"}, 
         {"5661.02", "12907.2"}, 
         {"5711.74", "12921.7"}, 
         {"5813.16", "12947.4"}, 
         {"5863.87", "12958.6"}, 
         {"5914.59", "12968.8"}, 
         {"5965.3", "12977.9"}, 
         {"6016.01", "12985.9"}, 
         {"6066.72", "12992.9"}, 
         {"6117.44", "12998.8"}, 
         {"6168.15", "13003.6"}, 
         {"6218.86", "13007.4"}, 
         {"6269.57", "13010"}, 
         {"6320.29", "13011.7"}, 
         {"6371", "13012.2"}});
    model.func("int12").setIndex("fununit", "kg/m^3", 0);
    model.func().duplicate("int13", "int12");
    model.func("int13").set("funcname", "cp7");
    model.func("int13")
         .set("table", new String[][]{{"5153.5", "11042.7"}, 
         {"5204.61", "11058.5"}, 
         {"5255.32", "11071.8"}, 
         {"5306.04", "11085"}, 
         {"5356.75", "11098.3"}, 
         {"5407.46", "11116.6"}, 
         {"5458.17", "11131.6"}, 
         {"5508.89", "11145.7"}, 
         {"5559.6", "11159"}, 
         {"5610.31", "11171.5"}, 
         {"5661.02", "11183.2"}, 
         {"5711.74", "11194.1"}, 
         {"5813.16", "11213.4"}, 
         {"5863.87", "11221.9"}, 
         {"5914.59", "11229.5"}, 
         {"5965.3", "11236.4"}, 
         {"6016.01", "11242.4"}, 
         {"6066.72", "11247.7"}, 
         {"6117.44", "11252.1"}, 
         {"6168.15", "11255.7"}, 
         {"6218.86", "11258.6"}, 
         {"6269.57", "11260.6"}, 
         {"6320.29", "11261.8"}, 
         {"6371", "11262.2"}});
    model.func("int13").setIndex("fununit", "m/s", 0);
    model.func().duplicate("int14", "int13");
    model.func("int14").set("funcname", "cs7");
    model.func("int14")
         .set("table", new String[][]{{"5153.5", "3504.3"}, 
         {"5204.61", "3518.7"}, 
         {"5255.32", "3531.4"}, 
         {"5306.04", "3543.5"}, 
         {"5356.75", "3555.1"}, 
         {"5407.46", "3566.1"}, 
         {"5458.17", "3576.5"}, 
         {"5508.89", "3586.4"}, 
         {"5559.6", "3595.7"}, 
         {"5610.31", "3604.4"}, 
         {"5661.02", "3612.6"}, 
         {"5711.74", "3620.2"}, 
         {"5813.16", "3633.7"}, 
         {"5863.87", "3639.6"}, 
         {"5914.59", "3645"}, 
         {"5965.3", "3649.8"}, 
         {"6016.01", "3654"}, 
         {"6066.72", "3657.7"}, 
         {"6117.44", "3660.8"}, 
         {"6168.15", "3663.3"}, 
         {"6218.86", "3665.3"}, 
         {"6269.57", "3666.7"}, 
         {"6320.29", "3667.5"}, 
         {"6371", "3667.8"}});
    model.func("int14").setIndex("fununit", "m/s", 0);

    model.nodeGroup().create("grp1", "GlobalDefinitions");
    model.nodeGroup("grp1").set("type", "func");
    model.nodeGroup("grp1").placeAfter("func", "an2");
    model.nodeGroup("grp1").add("func", "int1");
    model.nodeGroup("grp1").add("func", "int2");
    model.nodeGroup("grp1").add("func", "int3");
    model.nodeGroup().create("grp2", "GlobalDefinitions");
    model.nodeGroup("grp2").set("type", "func");
    model.nodeGroup("grp2").placeAfter("func", "an2");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("func", "int4");
    model.nodeGroup("grp2").add("func", "int5");
    model.nodeGroup("grp2").add("func", "int6");
    model.nodeGroup().create("grp3", "GlobalDefinitions");
    model.nodeGroup("grp3").set("type", "func");
    model.nodeGroup("grp3").placeAfter("func", "an2");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("func", "int7");
    model.nodeGroup("grp3").add("func", "int8");
    model.nodeGroup("grp3").add("func", "int9");
    model.nodeGroup().create("grp4", "GlobalDefinitions");
    model.nodeGroup("grp4").set("type", "func");
    model.nodeGroup("grp4").placeAfter("func", "an2");
    model.nodeGroup().move("grp4", 3);
    model.nodeGroup("grp4").add("func", "int10");
    model.nodeGroup("grp4").add("func", "int11");
    model.nodeGroup().create("grp5", "GlobalDefinitions");
    model.nodeGroup("grp5").set("type", "func");
    model.nodeGroup("grp5").placeAfter("func", "an2");
    model.nodeGroup().move("grp5", 4);
    model.nodeGroup("grp5").add("func", "int12");
    model.nodeGroup("grp5").add("func", "int13");
    model.nodeGroup("grp5").add("func", "int14");

    model.component("comp1").geom("geom1").lengthUnit("km");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_earth");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th1", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layername", "Layer 2", 1);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th2", 1);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layername", "Layer 3", 2);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th3", 2);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layername", "Layer 4", 3);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th4", 3);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layername", "Layer 5", 4);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th5", 4);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layername", "Layer 6", 5);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th6", 5);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layername", "Layer 7", 6);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "th7", 6);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "r0", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "z0", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "r_earth*sin(phi0)", 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "r_earth*cos(phi0)", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("depth", "r_earth-sqrt(r^2+z^2)");
    model.component("comp1").variable("var1").descr("depth", "Depth");

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").selection().set(18);
    model.component("comp1").probe("point1").set("expr", "v2z");
    model.component("comp1").probe("point1").set("unit", "mm/s");
    model.component("comp1").probe("point1").set("descractive", true);
    model.component("comp1").probe().duplicate("point2", "point1");
    model.component("comp1").probe("point2").selection().set(21);
    model.component("comp1").probe("point2").set("expr", "v2r*sin(phi0)+v2z*cos(phi0)");
    model.component("comp1").probe("point2").set("unit", "\u00b5m/s");
    model.component("comp1").probe().duplicate("point3", "point2");
    model.component("comp1").probe("point3").set("expr", "v2r*cos(phi0)-v2z*sin(phi0)");

    model.component("comp1").physics("pate").selection().set(6, 10);
    model.component("comp1").physics("elte").selection().set(1, 2, 3, 4, 5, 7, 8, 9, 11, 12, 13, 14, 15);
    model.component("comp1").physics("elte").feature("eltem1").set("IsotropicOption", "CpCs");
    model.component("comp1").physics("elte").create("bl1", "BodyLoad", 2);
    model.component("comp1").physics("elte").feature("bl1").selection().set(13);
    model.component("comp1").physics("elte").feature("bl1").set("LoadType", "TotalForce");
    model.component("comp1").physics("elte").feature("bl1")
         .set("Ftot", new String[]{"0", "0", "G_space(r,z)*G_time(t)"});
    model.component("comp1").physics("elte").create("mde1", "MaterialDiscontinuityElem", 1);
    model.component("comp1").physics("elte").feature("mde1").selection().set(26, 27, 28, 29, 36, 37, 38, 39);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(1, 15);
    model.component("comp1").material("mat1").propertyGroup().create("CpCs", "Pressure_wave_and_shear_wave_speeds");
    model.component("comp1").material("mat1").propertyGroup("CpCs").set("cp", new String[]{"cp1"});
    model.component("comp1").material("mat1").propertyGroup("CpCs").set("cs", new String[]{"cs1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho1"});
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").selection().set(2, 14);
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho2"});
    model.component("comp1").material("mat2").propertyGroup("CpCs").set("cp", new String[]{"cp2"});
    model.component("comp1").material("mat2").propertyGroup("CpCs").set("cs", new String[]{"cs2"});
    model.component("comp1").material().duplicate("mat3", "mat2");
    model.component("comp1").material("mat3").selection().set(3, 13);
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"rho3(depth)"});
    model.component("comp1").material("mat3").propertyGroup("CpCs").set("cp", new String[]{"cp3(depth)"});
    model.component("comp1").material("mat3").propertyGroup("CpCs").set("cs", new String[]{"cs3(depth)"});
    model.component("comp1").material().duplicate("mat4", "mat3");
    model.component("comp1").material("mat4").selection().set(4, 12);
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"rho4(depth)"});
    model.component("comp1").material("mat4").propertyGroup("CpCs").set("cp", new String[]{"cp4(depth)"});
    model.component("comp1").material("mat4").propertyGroup("CpCs").set("cs", new String[]{"cs4(depth)"});
    model.component("comp1").material().duplicate("mat5", "mat4");
    model.component("comp1").material("mat5").selection().set(5, 11);
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"rho5(depth)"});
    model.component("comp1").material("mat5").propertyGroup("CpCs").set("cp", new String[]{"cp5(depth)"});
    model.component("comp1").material("mat5").propertyGroup("CpCs").set("cs", new String[]{"cs5(depth)"});
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").selection().set(6, 10);
    model.component("comp1").material("mat6").propertyGroup("def").set("density", new String[]{"rho6(depth)"});
    model.component("comp1").material("mat6").propertyGroup("def").set("soundspeed", new String[]{"cp6(depth)"});
    model.component("comp1").material().duplicate("mat7", "mat5");
    model.component("comp1").material("mat7").selection().set(7, 9);
    model.component("comp1").material("mat7").propertyGroup("def").set("density", new String[]{"rho7(depth)"});
    model.component("comp1").material("mat7").propertyGroup("CpCs").set("cp", new String[]{"cp7(depth)"});
    model.component("comp1").material("mat7").propertyGroup("CpCs").set("cs", new String[]{"cs7(depth)"});
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").selection().set(8);
    model.component("comp1").material("mat8").propertyGroup().create("CpCs", "Pressure_wave_and_shear_wave_speeds");
    model.component("comp1").material("mat8").propertyGroup("CpCs").set("cp", new String[]{"cp8"});
    model.component("comp1").material("mat8").propertyGroup("CpCs").set("cs", new String[]{"cs8"});
    model.component("comp1").material("mat8").propertyGroup("def").set("density", new String[]{"rho8"});

    model.component("comp1").multiphysics().create("asbte1", "AcousticStructureBoundaryTimeExplicit", 1);
    model.component("comp1").multiphysics("asbte1").selection().set(30, 31, 34, 35);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 2, 14, 15);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "cs1/f0/2.0");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmin", "cs1/f0/2.0");
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").run("conv1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(3, 13);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "cs3(th1+th2)/f0/1.5");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(4, 12);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "cs4(th1+th2+th3)/f0/1.5");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().set(5, 11);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3")
         .set("hmax", "cs5(th1+th2+th3+th4)/f0/1.5");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size4", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").selection().set(6, 10);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size4")
         .set("hmax", "cp6(th1+th2+th3+th4+th5)/f0/1.5");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size5", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5").selection().set(7, 9);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size5")
         .set("hmax", "cs7(th1+th2+th3+th4+th5+th6)/f0/1.5");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size6", "Size");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size6").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size6").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size6").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size6").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size6").set("hmax", "cs8/f0/1.5");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,100,1400)");
    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("time").set("notlistsolnum", 1);
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("listsolnum", 1);
    model.study("std1").feature("time").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("tx1", "TimeExplicit");
    model.sol("sol1").feature("tx1").set("tlist", "range(0,100,1400)");
    model.sol("sol1").feature("tx1").set("plot", "off");
    model.sol("sol1").feature("tx1").set("plotgroup", "Default");
    model.sol("sol1").feature("tx1").set("plotfreq", "tout");
    model.sol("sol1").feature("tx1").set("probesel", "all");
    model.sol("sol1").feature("tx1").set("probes", new String[]{"point1", "point2", "point3"});
    model.sol("sol1").feature("tx1").set("probefreq", "tsteps");
    model.sol("sol1").feature("tx1").set("exprs", new String[]{"root.comp1.pate.wtc", "root.comp1.elte.wtc"});
    model.sol("sol1").feature("tx1").set("tstepping", "elemexprs");
    model.sol("sol1").feature("tx1").set("control", "time");
    model.sol("sol1").feature("tx1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("tx1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("tx1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");
    model.component("comp1").probe("point3").genResult("none");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().dataset().duplicate("dset3", "dset1");
    model.result().dataset("dset3").selection().geom("geom1", 1);
    model.result().dataset("dset3").selection().geom("geom1", 1);
    model.result().dataset("dset3").selection().set(25, 26, 27, 28, 29, 30, 31, 34, 35, 36, 37, 38, 39, 40, 41);
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").set("data", "dset3");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "manual");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("legendactive", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "if(isnan(pate.wtc),elte.wtc,pate.wtc)");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").set("data", "mir2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "mir1");
    model.result("pg3").feature("surf1").set("expr", "if(isnan(pate.v_inst),elte.vel,pate.v_inst)");
    model.result("pg3").feature("surf1").set("unit", "\u00b5m/s");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormax", 50);
    model.result("pg3").feature("surf1").set("colortable", "GrayScale");
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg3").feature("surf1").set("resolution", "custom");
    model.result("pg3").feature("surf1").set("refine", 6);
    model.result("pg3").feature("surf1").set("smooth", "everywhere");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "0");
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg4").feature("surf1").set("resolution", "custom");
    model.result("pg4").feature("surf1").set("refine", 6);
    model.result("pg4").feature("surf1").create("img1", "ImageOverlay");
    model.result("pg4").feature("surf1").feature("img1").set("filename", "seismic_waves_earth_image.jpg");
    model.result("pg4").feature("surf1").feature("img1").set("mapping", "spherical");
    model.result("pg4").feature("surf1").feature("img1").set("axistype", "cartesian");
    model.result("pg4").feature("surf1").feature("img1").set("ax3", new String[]{"0.18", "0.45", "1"});
    model.result("pg4").feature("surf1").feature("img1").set("rot", "270");
    model.result("pg4").run();

    model.view("view4").set("showgrid", false);

    model.result("pg4").run();
    model.result("pg4").feature("surf1").create("filt1", "Filter");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("filt1").set("expr", "sqrt(r^2+z^2)>6369[km]");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "elte.vel");
    model.result("pg4").feature("surf2").set("descractive", true);
    model.result("pg4").feature("surf2").set("unit", "\u00b5m/s");
    model.result("pg4").feature("surf2").set("rangecoloractive", true);
    model.result("pg4").feature("surf2").set("rangecolormax", 50);
    model.result("pg4").feature("surf2").set("colortable", "Thermal");
    model.result("pg4").feature("surf2").set("resolution", "custom");
    model.result("pg4").feature("surf2").set("refine", 6);
    model.result("pg4").feature("surf2").create("filt1", "Filter");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf2").feature("mtrl1").set("family", "soil");
    model.result("pg4").feature("surf2").feature("mtrl1").set("useplotcolors", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("filt1").set("expr", "sqrt(r^2+z^2)<6369[km]");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("expr", "pate.v_inst");
    model.result("pg4").feature("surf3").set("unit", "\u00b5m/s");
    model.result("pg4").feature("surf3").set("titletype", "none");
    model.result("pg4").feature("surf3").set("rangecoloractive", true);
    model.result("pg4").feature("surf3").set("rangecolormax", 50);
    model.result("pg4").feature("surf3").set("coloring", "gradient");
    model.result("pg4").feature("surf3").set("topcolor", "black");
    model.result("pg4").feature("surf3").set("bottomcolor", "custom");
    model.result("pg4").feature("surf3")
         .set("custombottomcolor", new double[]{0.9921568632125854, 0.7254902124404907, 0.07450980693101883});
    model.result("pg4").feature("surf3").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").feature("surf3").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf3").feature("mtrl1").set("family", "soil");
    model.result("pg4").feature("surf3").feature("mtrl1").set("useplotcolors", true);
    model.result().table("tbl1").set("tablebuffersize", 20000);

    model.sol("sol1").feature("v1").feature("comp1_e").set("out", false);
    model.sol("sol1").feature("v1").feature("comp1_asbte1_veig").set("out", false);
    model.sol("sol1").feature("v1").feature("comp1_asbte1_eig").set("out", false);
    model.sol("sol1").feature("v1").feature("comp1_p").set("out", false);
    model.sol("sol1").feature("v1").feature("comp1_elte_mde1_eigd").set("out", false);
    model.sol("sol1").feature("v1").feature("comp1_elte_mde1_eigu").set("out", false);
    model.sol("sol1").feature("v1").feature("comp1_elte_mde1_veigd").set("out", false);
    model.sol("sol1").feature("v1").feature("comp1_elte_mde1_veigu").set("out", false);

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotgroup", "pg4");

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");
    model.component("comp1").probe("point3").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("yseclabelactive", true);
    model.result("pg5").set("legendpos", "middleright");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg5").feature("lngr1").set("expr", "if(isnan(pate.c),elte.cp,pate.c)");
    model.result("pg5").feature("lngr1").set("unit", "km/s");
    model.result("pg5").feature("lngr1").set("titletype", "none");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "depth");
    model.result("pg5").feature("lngr1").set("linewidth", 3);
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "Pressure wave", 0);
    model.result("pg5").feature("lngr1").set("resolution", "extrafine");
    model.result("pg5").feature("lngr1").set("smooth", "everywhere");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "if(isnan(pate.c),elte.cs,0)");
    model.result("pg5").feature("lngr2").setIndex("legends", "Shear wave", 0);
    model.result("pg5").feature().duplicate("lngr3", "lngr2");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").set("expr", "if(isnan(pate.rho),elte.rho,pate.rho)");
    model.result("pg5").feature("lngr3").set("unit", "g/cm^3");
    model.result("pg5").feature("lngr3").set("plotonsecyaxis", true);
    model.result("pg5").feature("lngr3").setIndex("legends", "Density", 0);
    model.result("pg5").run();
    model.result("pg5").create("ann1", "Annotation");
    model.result("pg5").feature("ann1").set("plotonsecyaxis", true);
    model.result("pg5").feature("ann1").set("showpoint", false);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("ann2", "ann1");
    model.result("pg5").run();
    model.result("pg5").feature("ann2").set("posyexpr", 13.65);
    model.result("pg5").run();
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").run();
    model.result("pg3").run();

    model.view("view3").set("showgrid", false);

    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 6, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 7, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 9, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 13, 0);
    model.result("pg3").run();

    model.view("view3").set("showgrid", true);

    model.result("pg3").set("titletype", "auto");
    model.result("pg3").setIndex("looplevel", 15, 0);
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").run();

    model.title("\u5730\u9707\u6ce2\u5728\u5730\u7403\u5185\u90e8\u7684\u4f20\u64ad");

    model
         .description("\u672c\u6559\u7a0b\u5206\u6790\u5730\u9707\u6ce2\u7a7f\u8fc7\u5730\u7403\u5185\u90e8\u7ed3\u6784\u7684\u4f20\u64ad\uff0c\u5176\u4e2d\u4f7f\u7528\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u51e0\u4f55\u6765\u8868\u793a\u6750\u6599\u7684\u4e0d\u8fde\u7eed\u6027\u4ee5\u53ca\u7a7f\u8fc7\u5730\u7403\u540c\u5fc3\u5c42\u7684\u5c5e\u6027\u53d8\u5316\u3002\u901a\u8fc7\u7531\u97f3\u8c03\u8109\u51b2\u7ec4\u6210\u7684\u5730\u9707\u7b80\u5316\u6a21\u578b\u6765\u7814\u7a76\u4e0d\u540c\u538b\u529b\u6ce2\u548c\u526a\u5207\u6ce2\u5728\u5730\u7403\u5185\u90e8\u7ed3\u6784\u4e2d\u7684\u4ea7\u751f\u548c\u4f20\u64ad\u3002\n\n\u8be5\u6a21\u578b\u4f7f\u7528\u201c\u5f39\u6027\u6ce2\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u548c\u201c\u538b\u529b\u58f0\u5b66\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u63a5\u53e3\u6765\u8868\u793a\u5730\u7403\u7684\u56fa\u4f53\u548c\u6d41\u4f53\u90e8\u5206\u3002\u7a81\u7136\u51fa\u73b0\u7684\u5c5e\u6027\u4e0d\u8fde\u7eed\u901a\u8fc7\u4f7f\u7528\u201c\u6750\u6599\u4e0d\u8fde\u7eed\u201d\u7279\u5f81\u8fdb\u884c\u5904\u7406\uff0c\u800c\u5c5e\u6027\u7684\u7f13\u6162\u53d8\u5316\u5219\u5305\u542b\u5728\u6bcf\u4e00\u5c42\u7684\u6750\u6599\u5c5e\u6027\u4e2d\u3002\u8be5\u6a21\u578b\u6f14\u793a\u4e86\u65f6\u57df\u663e\u5f0f\u63a5\u53e3\u7684\u53ef\u6269\u5c55\u6027\uff0c\u53ca\u5176\u5728\u6355\u83b7\u5927\u578b\u548c\u8d85\u5927\u578b\u58f0\u5b66\u6a21\u578b\uff08\u5305\u542b\u8bb8\u591a\u6ce2\u957f\uff09\u65f6\u7684\u9002\u7528\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("seismic_waves_earth.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
