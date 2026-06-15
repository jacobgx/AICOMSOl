/*
 * squeeze_film_damper_damping_coefficients.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:49 by COMSOL 6.3.0.290. */
public class squeeze_film_damper_damping_coefficients {

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

    model.param().set("fr", "100[rpm]");
    model.param().descr("fr", "\u6da1\u52a8\u901f\u5ea6");
    model.param().set("R", "10[cm]");
    model.param().descr("R", "\u8f74\u9888\u534a\u5f84");
    model.param().set("L", "1[cm]");
    model.param().descr("L", "\u8f74\u9888\u957f\u5ea6");
    model.param().set("C", "1000[um]");
    model.param().descr("C", "\u95f4\u9699");
    model.param().set("e", "0");
    model.param().descr("e", "\u504f\u5fc3\u7387");
    model.param().set("phi", "10[deg]");
    model.param().descr("phi", "\u59ff\u6001\u89d2");
    model.param().set("mu0", "20[mPa*s]");
    model.param().descr("mu0", "\u6cb9\u7684\u9ecf\u5ea6");
    model.param().set("rho0", "864[kg/m^3]");
    model.param().descr("rho0", "\u6cb9\u7684\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").run("cyl1");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("c0", "mu0*R*(L/C)^3");
    model.component("comp1").variable("var1").descr("c0", "\u963b\u5c3c\u7f29\u653e");
    model.component("comp1").variable("var1").set("c_rr", "pi*(1+2*e^2)/(2*(1-e^2)^2.5)");
    model.component("comp1").variable("var1").descr("c_rr", "\u963b\u5c3c\u7cfb\u6570\uff0crr \u5206\u91cf");
    model.component("comp1").variable("var1").set("c_rt", "2*e/(1-e^2)^2");
    model.component("comp1").variable("var1").descr("c_rt", "\u963b\u5c3c\u7cfb\u6570\uff0crt \u5206\u91cf");
    model.component("comp1").variable("var1").set("c_tr", "c_rt");
    model.component("comp1").variable("var1").descr("c_tr", "\u963b\u5c3c\u7cfb\u6570\uff0ctr \u5206\u91cf");
    model.component("comp1").variable("var1").set("c_tt", "pi/(2*(1-e^2)^1.5)");
    model.component("comp1").variable("var1").descr("c_tt", "\u963b\u5c3c\u7cfb\u6570\uff0ctt \u5206\u91cf");
    model.component("comp1").variable("var1").set("c_22", "c_rr*(sin(phi))^2+c_rt*sin(2*phi)+c_tt*(cos(phi))^2");
    model.component("comp1").variable("var1").descr("c_22", "\u963b\u5c3c\u7cfb\u6570\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1").set("c_23", "(-c_rr+c_tt)*sin(phi)*cos(phi)-c_rt*cos(2*phi)");
    model.component("comp1").variable("var1").descr("c_23", "\u963b\u5c3c\u7cfb\u6570\uff0c23 \u5206\u91cf");
    model.component("comp1").variable("var1").set("c_32", "c_23");
    model.component("comp1").variable("var1").descr("c_32", "\u963b\u5c3c\u7cfb\u6570\uff0c32 \u5206\u91cf");
    model.component("comp1").variable("var1").set("c_33", "c_rr*(cos(phi))^2-c_rt*sin(2*phi)+c_tt*(sin(phi))^2");
    model.component("comp1").variable("var1").descr("c_33", "\u963b\u5c3c\u7cfb\u6570\uff0c33 \u5206\u91cf");

    model.component("comp1").physics("hdb").prop("DynamicCoefficients").set("calculateDynamicCoefficients", true);
    model.component("comp1").physics("hdb").create("sfd1", "SqueezeFilmDamper", 2);
    model.component("comp1").physics("hdb").feature("sfd1").selection().all();
    model.component("comp1").physics("hdb").feature("sfd1").set("C", "C");
    model.component("comp1").physics("hdb").feature("sfd1").set("BearingCenterType", "fromGeom");
    model.component("comp1").physics("hdb").feature("sfd1").set("Specify", "Eccentricity");
    model.component("comp1").physics("hdb").feature("sfd1").set("ec", "C*e");
    model.component("comp1").physics("hdb").feature("sfd1").set("phiy", "270[deg]+phi");
    model.component("comp1").physics("hdb").feature("sfd1").set("Ow", "2*pi[rad]*fr");
    model.component("comp1").physics("hdb").feature("sfd1").set("mure_mat", "userdef");
    model.component("comp1").physics("hdb").feature("sfd1").set("mure", "mu0");
    model.component("comp1").physics("hdb").feature("sfd1").set("rho_mat", "userdef");
    model.component("comp1").physics("hdb").feature("sfd1").set("rho", "rho0");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 2, 4, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "fr", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "1/s", 0);
    model.study("std1").feature("stat").setIndex("pname", "fr", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "1/s", 0);
    model.study("std1").feature("stat").setIndex("pname", "e", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0.02,0.02,0.96)", 0);
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
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"hdb.sfd1.c22"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u5e73\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c22 \u5206\u91cf"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"N*s/m"});
    model.result("pg2").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg2").feature("glob1").setIndex("descr", "c<sub>22</sub>, COMSOL", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "-hdb.sfd1.c23", 1);
    model.result("pg2").feature("glob1").setIndex("unit", 1, 1);
    model.result("pg2").feature("glob1").setIndex("descr", "-c<sub>23</sub>, COMSOL", 1);
    model.result("pg2").feature("glob1").setIndex("expr", "-hdb.sfd1.c32", 2);
    model.result("pg2").feature("glob1").setIndex("unit", 1, 2);
    model.result("pg2").feature("glob1").setIndex("descr", "-c<sub>32</sub>, COMSOL", 2);
    model.result("pg2").feature("glob1").setIndex("expr", "hdb.sfd1.c33", 3);
    model.result("pg2").feature("glob1").setIndex("unit", 1, 3);
    model.result("pg2").feature("glob1").setIndex("descr", "c<sub>33</sub>, COMSOL", 3);
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").setIndex("expr", "c_22*c0", 0);
    model.result("pg2").feature("glob2").setIndex("unit", "", 0);
    model.result("pg2").feature("glob2").setIndex("descr", "c<sub>22</sub>, \u89e3\u6790\u503c", 0);
    model.result("pg2").feature("glob2").setIndex("expr", "-c_23*c0", 1);
    model.result("pg2").feature("glob2").setIndex("unit", "", 1);
    model.result("pg2").feature("glob2").setIndex("descr", "-c<sub>23</sub>, \u89e3\u6790\u503c", 1);
    model.result("pg2").feature("glob2").setIndex("expr", "-c_32*c0", 2);
    model.result("pg2").feature("glob2").setIndex("unit", "", 2);
    model.result("pg2").feature("glob2").setIndex("descr", "-c<sub>32</sub>, \u89e3\u6790\u503c", 2);
    model.result("pg2").feature("glob2").setIndex("expr", "c_33*c0", 3);
    model.result("pg2").feature("glob2").setIndex("unit", "", 3);
    model.result("pg2").feature("glob2").setIndex("descr", "c<sub>33</sub>, \u89e3\u6790\u503c", 3);
    model.result("pg2").feature("glob2").set("linestyle", "none");
    model.result("pg2").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg2").feature("glob2").set("linemarker", "circle");
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u76f8\u5bf9\u504f\u5fc3\u7387 (e)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").set("ylog", true);
    model.result("pg2").run();

    model.title("\u6324\u538b\u6cb9\u819c\u963b\u5c3c\u5668\u7684\u963b\u5c3c\u7cfb\u6570");

    model
         .description("\u6324\u538b\u6cb9\u819c\u963b\u5c3c\u5668\u662f\u4e3a\u65cb\u8f6c\u673a\u68b0\u63d0\u4f9b\u989d\u5916\u963b\u5c3c\u7684\u90e8\u4ef6\u3002\u4e3a\u4e86\u7b80\u5316\u8f6c\u5b50\u603b\u6210\u5efa\u6a21\uff0c\u6211\u4eec\u901a\u5e38\u4f1a\u6839\u636e\u6324\u538b\u6cb9\u819c\u963b\u5c3c\u5668\u7684\u963b\u5c3c\u7cfb\u6570\u5bf9\u5176\u8fdb\u884c\u5efa\u6a21\uff0c\u8fd9\u4e9b\u7cfb\u6570\u662f\u963b\u5c3c\u5668\u4e2d\u8f74\u9888\u4f4d\u7f6e\u7684\u51fd\u6570\u3002\u672c\u6a21\u578b\u8ba1\u7b97\u77ed\u6324\u538b\u6cb9\u819c\u963b\u5c3c\u5668\u7684\u963b\u5c3c\u7cfb\u6570\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u76f8\u5e94\u7684\u89e3\u6790\u503c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("squeeze_film_damper_damping_coefficients.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
