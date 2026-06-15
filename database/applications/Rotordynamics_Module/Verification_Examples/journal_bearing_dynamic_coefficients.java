/*
 * journal_bearing_dynamic_coefficients.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:49 by COMSOL 6.3.0.290. */
public class journal_bearing_dynamic_coefficients {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/hdb", true);

    model.param().set("R", "10[cm]");
    model.param().descr("R", "\u8f74\u9888\u534a\u5f84");
    model.param().set("L", "2[cm]");
    model.param().set("C", "100[um]");
    model.param().descr("C", "\u95f4\u9699");
    model.param().set("Omega", "1000[rad/s]");
    model.param().descr("Omega", "\u89d2\u901f\u5ea6");
    model.param().set("mu0", "20[mPa*s]");
    model.param().descr("mu0", "\u6da6\u6ed1\u6cb9\u9ecf\u5ea6");
    model.param().set("rho0", "866[kg/m^3]");
    model.param().descr("rho0", "\u6da6\u6ed1\u6cb9\u5bc6\u5ea6");
    model.param().set("W", "500[N]");
    model.param().descr("W", "\u8f74\u627f\u4e0a\u7684\u9759\u8f7d\u8377");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("k0", "W/C");
    model.component("comp1").variable("var1").descr("k0", "\u521a\u5ea6\u7f29\u653e");
    model.component("comp1").variable("var1").set("c0", "W/(C*Omega)");
    model.component("comp1").variable("var1").descr("c0", "\u963b\u5c3c\u7f29\u653e");
    model.component("comp1").variable("var1").set("e", "hdb.hjb1.ec_rel");
    model.component("comp1").variable("var1").descr("e", "\u504f\u5fc3\u7387");
    model.component("comp1").variable("var1").set("phi0", "atan2(pi*sqrt(1-e^2),4*e)");
    model.component("comp1").variable("var1").descr("phi0", "\u59ff\u6001\u89d2");
    model.component("comp1").variable("var1").set("Q", "(pi^2+(16-pi^2)*e^2)^1.5");
    model.component("comp1").variable("var1").descr("Q", "\u8f85\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var1").set("k22", "4*(16*e^2+pi^2*(2-e^2))/Q");
    model.component("comp1").variable("var1").descr("k22", "\u65e0\u91cf\u7eb2\u521a\u5ea6\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1").set("k23", "pi*(pi^2*(1-e^2)-16*e^4)/(e*sqrt(1-e^2)*Q)");
    model.component("comp1").variable("var1").descr("k23", "\u65e0\u91cf\u7eb2\u521a\u5ea6\uff0c23 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("k32", "-pi*(pi^2*(1+2*e^2)*(1-e^2)+32*e^2*(1+e^2))/(e*sqrt(1-e^2)*Q)");
    model.component("comp1").variable("var1").descr("k32", "\u65e0\u91cf\u7eb2\u521a\u5ea6\uff0c32 \u5206\u91cf");
    model.component("comp1").variable("var1").set("k33", "4*(pi^2*(1+2*e^2)*(1-e^2)+32*e^2*(1+e^2))/((1-e^2)*Q)");
    model.component("comp1").variable("var1").descr("k33", "\u65e0\u91cf\u7eb2\u521a\u5ea6\uff0c33 \u5206\u91cf");
    model.component("comp1").variable("var1").set("c22", "2*pi*sqrt(1-e^2)*(pi^2*(1+2*e^2)-16*e^2)/(e*Q)");
    model.component("comp1").variable("var1").descr("c22", "\u65e0\u91cf\u7eb2\u963b\u5c3c\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1").set("c23", "8*(16*e^2-pi^2*(1+2*e^2))/Q");
    model.component("comp1").variable("var1").descr("c23", "\u65e0\u91cf\u7eb2\u963b\u5c3c\uff0c23 \u5206\u91cf");
    model.component("comp1").variable("var1").set("c32", "c23");
    model.component("comp1").variable("var1").descr("c32", "\u65e0\u91cf\u7eb2\u963b\u5c3c\uff0c32 \u5206\u91cf");
    model.component("comp1").variable("var1").set("c33", "2*pi*(48*e^2+pi^2*(1-e^2)^2)/(e*sqrt(1-e^2)*Q)");
    model.component("comp1").variable("var1").descr("c33", "\u65e0\u91cf\u7eb2\u963b\u5c3c\uff0c33 \u5206\u91cf");

    model.component("comp1").physics("hdb").prop("DynamicCoefficients").set("calculateDynamicCoefficients", true);
    model.component("comp1").physics("hdb").feature("hjb1").set("C_plain", "C");
    model.component("comp1").physics("hdb").feature("hjb1").set("BearingCenterType", "fromGeom");
    model.component("comp1").physics("hdb").feature("hjb1").set("Specify", "Load");
    model.component("comp1").physics("hdb").feature("hjb1").set("W", new String[]{"0", "0", "-W"});
    model.component("comp1").physics("hdb").feature("hjb1").set("Ow", "Omega");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure_mat", "userdef");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure", "mu0");
    model.component("comp1").physics("hdb").feature("hjb1").set("rho_mat", "userdef");
    model.component("comp1").physics("hdb").feature("hjb1").set("rho", "rho0");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 2, 4, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 12);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "R", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "R", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "W", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "10^range(1,0.2,5)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6d41\u4f53\u538b\u529b (hdb)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("smooth", "internal");
    model.result("pg1").feature("con1").set("inherittubescale", false);
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u504f\u5fc3\u7387");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"hdb.hjb1.ec_rel"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u504f\u5fc3\u7387"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").set("showlegends", false);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u59ff\u6001\u89d2");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"hdb.hjb1.phia"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u59ff\u6001\u89d2"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u59ff\u6001\u89d2\uff0cCOMSOL", 0);
    model.result("pg3").feature("glob1").set("linewidth", 3);
    model.result("pg3").run();
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").setIndex("expr", "phi0", 0);
    model.result("pg3").feature("glob2").setIndex("unit", "deg", 0);
    model.result("pg3").feature("glob2").setIndex("descr", "\u59ff\u6001\u89d2\uff0c\u89e3\u6790\u503c", 0);
    model.result("pg3").feature("glob2").set("linestyle", "none");
    model.result("pg3").feature("glob2").set("linemarker", "cycle");
    model.result("pg3").feature("glob2").set("markerpos", "interp");
    model.result("pg3").feature("glob2").set("markers", 50);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u59ff\u6001\u89d2 (deg)");
    model.result("pg3").set("titletype", "none");
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u538b\u529b\u548c\u819c\u539a");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("expr", new String[]{"hdb.hjb1.p_max"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u6700\u5927\u8f74\u627f\u538b\u529b"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"Pa"});
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("expr", new String[]{"hdb.hjb1.h_min"});
    model.result("pg4").feature("glob2").set("descr", new String[]{"\u6700\u5c0f\u819c\u539a"});
    model.result("pg4").feature("glob2").set("unit", new String[]{"m"});
    model.result("pg4").run();
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("legendpos", "uppermiddle");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8f74\u627f\u521a\u5ea6");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"hdb.hjb1.k22"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u5e73\u52a8\u521a\u5ea6\u7cfb\u6570\uff0c22 \u5206\u91cf"});
    model.result("pg5").feature("glob1").setIndex("descr", "k<sub>22</sub>, COMSOL", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "abs(hdb.hjb1.k23)", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "|k<sub>23</sub>|, COMSOL", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "-hdb.hjb1.k32", 2);
    model.result("pg5").feature("glob1").setIndex("descr", "-k<sub>32</sub>, COMSOL", 2);
    model.result("pg5").feature("glob1").setIndex("expr", "hdb.hjb1.k33", 3);
    model.result("pg5").feature("glob1").setIndex("descr", "k<sub>33</sub>, COMSOL", 3);
    model.result("pg5").feature("glob1").set("linewidth", 3);
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("xlog", true);
    model.result("pg5").run();
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2").setIndex("expr", "k22*k0", 0);
    model.result("pg5").feature("glob2").setIndex("expr", "abs(k23*k0)", 1);
    model.result("pg5").feature("glob2").setIndex("expr", "-k32*k0", 2);
    model.result("pg5").feature("glob2").setIndex("expr", "k33*k0", 3);
    model.result("pg5").feature("glob2").set("linestyle", "none");
    model.result("pg5").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg5").feature("glob2").set("linemarker", "circle");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("legendpos", "upperleft");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"hdb.hjb1.c22"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u5e73\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c22 \u5206\u91cf"});
    model.result("pg6").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg6").feature("glob1").setIndex("descr", "-c<sub>22</sub>, COMSOL", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "-hdb.hjb1.c23", 1);
    model.result("pg6").feature("glob1").setIndex("unit", 1, 1);
    model.result("pg6").feature("glob1").setIndex("descr", "-c<sub>23</sub>, COMSOL", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "-hdb.hjb1.c32", 2);
    model.result("pg6").feature("glob1").setIndex("unit", 1, 2);
    model.result("pg6").feature("glob1").setIndex("descr", "-c<sub>32</sub>, COMSOL", 2);
    model.result("pg6").feature("glob1").setIndex("expr", "hdb.hjb1.c33", 3);
    model.result("pg6").feature("glob1").setIndex("unit", 1, 3);
    model.result("pg6").feature("glob1").setIndex("descr", "c<sub>33</sub>, COMSOL", 3);
    model.result("pg6").feature("glob1").set("linewidth", 3);
    model.result("pg6").set("ylog", true);
    model.result("pg6").set("xlog", true);
    model.result("pg6").run();
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").setIndex("expr", "c22*c0", 0);
    model.result("pg6").feature("glob2").setIndex("unit", 1, 0);
    model.result("pg6").feature("glob2").setIndex("descr", "c<sub>22</sub>\uff0c\u89e3\u6790\u503c", 0);
    model.result("pg6").feature("glob2").setIndex("expr", "-c23*c0", 1);
    model.result("pg6").feature("glob2").setIndex("unit", 1, 1);
    model.result("pg6").feature("glob2").setIndex("descr", "-c<sub>23</sub>\uff0c\u89e3\u6790\u503c", 1);
    model.result("pg6").feature("glob2").setIndex("expr", "-c32*c0", 2);
    model.result("pg6").feature("glob2").setIndex("unit", 1, 2);
    model.result("pg6").feature("glob2").setIndex("descr", "-c<sub>32</sub>\uff0c\u89e3\u6790\u503c", 2);
    model.result("pg6").feature("glob2").setIndex("expr", "c33*c0", 3);
    model.result("pg6").feature("glob2").setIndex("unit", 1, 3);
    model.result("pg6").feature("glob2").setIndex("descr", "c<sub>33</sub>\uff0c\u89e3\u6790\u503c", 3);
    model.result("pg6").feature("glob2").set("linestyle", "none");
    model.result("pg6").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg6").feature("glob2").set("linemarker", "circle");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg5").run();

    model.title("\u5706\u67f1\u5f62\u8f74\u9888\u8f74\u627f\u7684\u52a8\u529b\u7cfb\u6570\u8ba1\u7b97");

    model
         .description("\u5728\u8f6c\u5b50\u5206\u6790\u8fc7\u7a0b\u4e2d\uff0c\u6211\u4eec\u5f80\u5f80\u901a\u8fc7\u8f74\u627f\u5728\u9759\u6001\u5e73\u8861\u4f4d\u7f6e\u7684\u6709\u6548\u52a8\u529b\u7cfb\u6570\u8fdb\u884c\u5efa\u6a21\u4eff\u771f\u3002\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u5706\u67f1\u5f62\u8f74\u9888\u8f74\u627f\u7684\u8fd9\u4e9b\u7cfb\u6570\uff0c\u4e3a\u4e86\u9a8c\u8bc1\u8ba1\u7b97\u51fa\u7684\u7cfb\u6570\u4e0e\u77ed\u8f74\u627f\u8fd1\u4f3c\u7684\u76f8\u5e94\u89e3\u6790\u503c\u76f8\u5339\u914d\uff0c\u7279\u610f\u5c06\u8f74\u627f\u957f\u5ea6\u8bbe\u4e3a\u8fdc\u5c0f\u4e8e\u5176\u76f4\u5f84\u3002");

    model.label("journal_bearing_dynamic_coefficients.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
