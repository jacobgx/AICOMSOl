/*
 * channel_beam.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:22 by COMSOL 6.3.0.290. */
public class channel_beam {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("beam", "HermitianBeam", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/beam", true);

    model.param().set("h1", "25[mm]");
    model.param().descr("h1", "\u7ffc\u7f18\u5bbd\u5ea6");
    model.param().set("h2", "50[mm]");
    model.param().descr("h2", "\u622a\u9762\u9ad8\u5ea6");
    model.param().set("t1", "5[mm]");
    model.param().descr("t1", "\u8179\u677f\u539a\u5ea6");
    model.param().set("t2", "6[mm]");
    model.param().descr("t2", "\u7ffc\u7f18\u539a\u5ea6");
    model.param().set("L", "1[m]");
    model.param().descr("L", "\u6881\u957f");
    model.param().set("Eb", "200[GPa]");
    model.param().descr("Eb", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nub", "0.25");
    model.param().descr("nub", "\u6cca\u677e\u6bd4");
    model.param().set("rhob", "8000[kg/m^3]");
    model.param().descr("rhob", "\u5bc6\u5ea6");
    model.param().set("FX", "10e3[N]");
    model.param().descr("FX", "X \u65b9\u5411\u7684\u529b");
    model.param().set("FY", "50[N]");
    model.param().descr("FY", "Y \u65b9\u5411\u7684\u529b");
    model.param().set("FZ", "100[N]");
    model.param().descr("FZ", "Z \u65b9\u5411\u7684\u529b");
    model.param().set("MX", "-10[N*m]");
    model.param().descr("MX", "X \u65b9\u5411\u7684\u529b\u77e9");

    model.group().create("lg1", "LoadGroup");
    model.group("lg1").label("\u8f7d\u8377\u7ec4\uff1a\u8fb9");
    model.group("lg1").paramName("lgE");
    model.group().create("lg2", "LoadGroup");
    model.group("lg2").label("\u8f7d\u8377\u7ec4\uff1a\u70b9");
    model.group("lg2").paramName("lgP");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"Eb"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nub"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhob"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Gb", "Eb/(2*(1+nub))");
    model.component("comp1").variable("var1").descr("Gb", "\u526a\u5207\u6a21\u91cf");
    model.component("comp1").variable("var1").set("A", "4.9e-4[m^2]");
    model.component("comp1").variable("var1").descr("A", "\u6a2a\u622a\u9762\u79ef");
    model.component("comp1").variable("var1").set("Iyy", "2.77e-8[m^4]");
    model.component("comp1").variable("var1").descr("Iyy", "\u9762\u79ef\u60ef\u6027\u77e9\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1").set("Izz", "1.69e-7[m^4]");
    model.component("comp1").variable("var1").descr("Izz", "\u9762\u79ef\u60ef\u6027\u77e9\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1").set("Jbeam", "5.18e-9[m^4]");
    model.component("comp1").variable("var1").descr("Jbeam", "\u626d\u8f6c\u5e38\u6570");
    model.component("comp1").variable("var1").set("Wt", "8.64e-7[m^3]");
    model.component("comp1").variable("var1").descr("Wt", "\u626d\u8f6c\u622a\u9762\u6a21\u91cf");
    model.component("comp1").variable("var1").set("ey", "0[m]");
    model.component("comp1").variable("var1")
         .descr("ey", "\u76f8\u5bf9\u4e8e\u8d28\u5fc3\u7684\u526a\u5207\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.component("comp1").variable("var1").set("ez", "0.0148[m]");
    model.component("comp1").variable("var1")
         .descr("ez", "\u76f8\u5bf9\u4e8e\u8d28\u5fc3\u7684\u526a\u5207\u4e2d\u5fc3\uff0cz \u5750\u6807");
    model.component("comp1").variable("var1").set("muy", "2.44");
    model.component("comp1").variable("var1")
         .descr("muy", "\u5c40\u90e8 y \u65b9\u5411\u7684\u6700\u5927\u526a\u5207\u5e94\u529b\u56e0\u5b50");
    model.component("comp1").variable("var1").set("muz", "2.38");
    model.component("comp1").variable("var1")
         .descr("muz", "\u5c40\u90e8 z \u65b9\u5411\u7684\u6700\u5927\u526a\u5207\u5e94\u529b\u56e0\u5b50");
    model.component("comp1").variable("var1").set("y1", "-0.025[m]");
    model.component("comp1").variable("var1").descr("y1", "\u8ba1\u7b97\u70b9 1\uff0c\u5c40\u90e8 y \u5750\u6807");
    model.component("comp1").variable("var1").set("z1", "-0.0164[m]");
    model.component("comp1").variable("var1").descr("z1", "\u8ba1\u7b97\u70b9 1\uff0c\u5c40\u90e8 z \u5750\u6807");
    model.component("comp1").variable("var1").set("y2", "0.025[m]");
    model.component("comp1").variable("var1").descr("y2", "\u8ba1\u7b97\u70b9 2\uff0c\u5c40\u90e8 y \u5750\u6807");
    model.component("comp1").variable("var1").set("z2", "-0.0164[m]");
    model.component("comp1").variable("var1").descr("z2", "\u8ba1\u7b97\u70b9 2\uff0c\u5c40\u90e8 z \u5750\u6807");
    model.component("comp1").variable("var1").set("y3", "0.025[m]");
    model.component("comp1").variable("var1").descr("y3", "\u8ba1\u7b97\u70b9 3\uff0c\u5c40\u90e8 y \u5750\u6807");
    model.component("comp1").variable("var1").set("z3", "0.0086[m]");
    model.component("comp1").variable("var1").descr("z3", "\u8ba1\u7b97\u70b9 3\uff0c\u5c40\u90e8 z \u5750\u6807");
    model.component("comp1").variable("var1").set("y4", "-0.025[m]");
    model.component("comp1").variable("var1").descr("y4", "\u8ba1\u7b97\u70b9 4\uff0c\u5c40\u90e8 y \u5750\u6807");
    model.component("comp1").variable("var1").set("z4", "0.0086[m]");
    model.component("comp1").variable("var1").descr("z4", "\u8ba1\u7b97\u70b9 4\uff0c\u5c40\u90e8 z \u5750\u6807");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "sigmabx");
    model.component("comp1").func("an1").set("expr", "-FZ*L*y/comp1.Izz+FY*L*z/comp1.Iyy");
    model.component("comp1").func("an1").set("args", "y, z");
    model.component("comp1").func("an1").setIndex("plotargs", "-h2/2", 0, 1);
    model.component("comp1").func("an1").setIndex("plotargs", "h2/2", 0, 2);
    model.component("comp1").func("an1").setIndex("plotargs", "-h1/2", 1, 1);
    model.component("comp1").func("an1").setIndex("plotargs", "h1/2", 1, 2);
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").set("fununit", "N/m^2");
    model.component("comp1").func("an1").label("sigmabx");

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("deltaX", "FX*L/(Eb*A)");
    model.component("comp1").variable("var2").descr("deltaX", "X \u4f4d\u79fb");
    model.component("comp1").variable("var2").set("deltaY", "FY*L^3/(3*Eb*Iyy)");
    model.component("comp1").variable("var2").descr("deltaY", "Y \u4f4d\u79fb");
    model.component("comp1").variable("var2").set("deltaZ", "FZ*L^3/(3*Eb*Izz)");
    model.component("comp1").variable("var2").descr("deltaZ", "Z \u4f4d\u79fb");
    model.component("comp1").variable("var2").set("thetaX", "MX*L/(Gb*Jbeam)");
    model.component("comp1").variable("var2").descr("thetaX", "\u626d\u8f6c");
    model.component("comp1").variable("var2").set("sigmax_Fx", "FX/A");
    model.component("comp1").variable("var2")
         .descr("sigmax_Fx", "\u8f74\u5411\u8f7d\u8377\u4ea7\u751f\u7684\u5e94\u529b");
    model.component("comp1").variable("var2").set("tausy_max", "muy*FZ/A");
    model.component("comp1").variable("var2")
         .descr("tausy_max", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b");
    model.component("comp1").variable("var2").set("tausz_max", "-muz*FY/A");
    model.component("comp1").variable("var2")
         .descr("tausz_max", "z \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b");
    model.component("comp1").variable("var2").set("taut_max", "abs(MX)/Wt");
    model.component("comp1").variable("var2")
         .descr("taut_max", "\u626d\u8f6c\u5f15\u8d77\u7684\u526a\u5207\u5e94\u529b");
    model.component("comp1").variable("var2").set("tauxz_max", "abs(tausz_max)+taut_max");
    model.component("comp1").variable("var2")
         .descr("tauxz_max", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var2").set("tauxy_max", "abs(tausy_max)+taut_max");
    model.component("comp1").variable("var2")
         .descr("tauxy_max", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var2").set("sigx1", "sigmax_Fx+sigmabx(y1,z1)");
    model.component("comp1").variable("var2").descr("sigx1", "\u70b9 1 \u5904\u7684\u6b63\u5e94\u529b");
    model.component("comp1").variable("var2").set("sigx2", "sigmax_Fx+sigmabx(y2,z2)");
    model.component("comp1").variable("var2").descr("sigx2", "\u70b9 2 \u5904\u7684\u6b63\u5e94\u529b");
    model.component("comp1").variable("var2").set("sigx3", "sigmax_Fx+sigmabx(y3,z3)");
    model.component("comp1").variable("var2").descr("sigx3", "\u70b9 3 \u5904\u7684\u6b63\u5e94\u529b");
    model.component("comp1").variable("var2").set("sigx4", "sigmax_Fx+sigmabx(y4,z4)");
    model.component("comp1").variable("var2").descr("sigx4", "\u70b9 4 \u5904\u7684\u6b63\u5e94\u529b");
    model.component("comp1").variable("var2").set("sigx_max", "max(max(max(sigx1,sigx2),sigx3),sigx4)");
    model.component("comp1").variable("var2")
         .descr("sigx_max", "\u6a2a\u622a\u9762\u4e2d\u7684\u6700\u5927\u6b63\u5e94\u529b");
    model.component("comp1").variable("var2").set("sig_mises", "sqrt(sigx_max^2+3*tauxy_max^2+3*tauxz_max^2)");
    model.component("comp1").variable("var2").descr("sig_mises", "\u6700\u5927 von Mises \u5e94\u529b");
    model.component("comp1").variable("var2").set("deltaZ_g", "-rhob*g_const*A*L^4/(8*Eb*Izz)");
    model.component("comp1").variable("var2")
         .descr("deltaZ_g", "\u91cd\u529b\u8f7d\u8377\u5f15\u8d77\u7684 Z \u4f4d\u79fb");
    model.component("comp1").variable("var2").set("thetaX_g", "rhob*g_const*A*ez*L^2/(2*Gb*Jbeam)");
    model.component("comp1").variable("var2")
         .descr("thetaX_g", "\u91cd\u529b\u8f7d\u8377\u5f15\u8d77\u7684\u626d\u8f6c");

    model.component("comp1").physics("beam").feature("csd1").set("SectionType", "U_Profile");
    model.component("comp1").physics("beam").feature("csd1").set("hy_U", "h2");
    model.component("comp1").physics("beam").feature("csd1").set("hz_U", "h1");
    model.component("comp1").physics("beam").feature("csd1").set("ty_U", "t2");
    model.component("comp1").physics("beam").feature("csd1").set("tz_U", "t1");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("vector_beam", new int[]{0, 0, 1});
    model.component("comp1").physics("beam").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("beam").feature("gacc1").set("loadGroup", "lg1");
    model.component("comp1").physics("beam").create("fix1", "Fixed", 0);
    model.component("comp1").physics("beam").feature("fix1").selection().set(1);
    model.component("comp1").physics("beam").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("beam").feature("pl1").selection().set(2);
    model.component("comp1").physics("beam").feature("pl1").set("forcePoint", new String[]{"FX", "FY", "FZ"});
    model.component("comp1").physics("beam").feature("pl1").set("momentPoint", new String[]{"MX", "0", "0"});
    model.component("comp1").physics("beam").feature("pl1").set("loadGroup", "lg2");

    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u70b9\u8f7d\u8377", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8fb9\u8f7d\u8377", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 0);
    model.study("std1").label("\u7a33\u6001\u7814\u7a76\uff1a\u6881");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1beam", "Beam");
    model.result().dataset("dset1beam").set("data", "dset1");
    model.result().dataset("dset1beam").set("physicsinterface", "beam");
    model.result().dataset("dset1beam").set("refinement", 2);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1beam");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5e94\u529b\uff0c\u4e09\u7ef4 (beam)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"beam.misesS"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"beam.uS", "beam.vS", "beam.wS"});
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").label("\u6848\u4f8b 1\uff1a\u4f4d\u79fb/\u65cb\u8f6c");
    model.result().numerical("pev1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("pev1").selection().set(2);
    model.result().numerical("pev1").set("expr", new String[]{"u"});
    model.result().numerical("pev1").set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf"});
    model.result().numerical("pev1").set("unit", new String[]{"m"});
    model.result().numerical("pev1").set("expr", new String[]{"u", "deltaX"});
    model.result().numerical("pev1")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf", "X \u4f4d\u79fb"});
    model.result().numerical("pev1").set("expr", new String[]{"u", "deltaX", "v"});
    model.result().numerical("pev1")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf", "X \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf"});
    model.result().numerical("pev1").set("expr", new String[]{"u", "deltaX", "v", "deltaY"});
    model.result().numerical("pev1")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf", "X \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf", "Y \u4f4d\u79fb"});
    model.result().numerical("pev1").set("expr", new String[]{"u", "deltaX", "v", "deltaY", "w"});
    model.result().numerical("pev1")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf", "X \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf", "Y \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf"});
    model.result().numerical("pev1").set("expr", new String[]{"u", "deltaX", "v", "deltaY", "w", "deltaZ"});
    model.result().numerical("pev1")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf", "X \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf", "Y \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf", "Z \u4f4d\u79fb"});
    model.result().numerical("pev1").set("expr", new String[]{"u", "deltaX", "v", "deltaY", "w", "deltaZ", "thx"});
    model.result().numerical("pev1")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf", "X \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf", "Y \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf", "Z \u4f4d\u79fb", "\u65cb\u8f6c\u573a\uff0cX \u5206\u91cf"});
    model.result().numerical("pev1")
         .set("expr", new String[]{"u", "deltaX", "v", "deltaY", "w", "deltaZ", "thx", "thetaX"});
    model.result().numerical("pev1")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf", "X \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf", "Y \u4f4d\u79fb", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf", "Z \u4f4d\u79fb", "\u65cb\u8f6c\u573a\uff0cX \u5206\u91cf", "\u626d\u8f6c"});
    model.result().numerical("pev1").setIndex("descr", "delta_x \u8ba1\u7b97\u503c", 0);
    model.result().numerical("pev1").setIndex("descr", "delta_x \u89e3\u6790\u503c", 1);
    model.result().numerical("pev1").setIndex("descr", "delta_y \u8ba1\u7b97\u503c", 2);
    model.result().numerical("pev1").setIndex("descr", "delta_y \u89e3\u6790\u503c", 3);
    model.result().numerical("pev1").setIndex("descr", "delta_z \u8ba1\u7b97\u503c", 4);
    model.result().numerical("pev1").setIndex("descr", "delta_z \u89e3\u6790\u503c", 5);
    model.result().numerical("pev1").setIndex("descr", "theta_x \u8ba1\u7b97\u503c", 6);
    model.result().numerical("pev1").setIndex("descr", "theta_x \u89e3\u6790\u503c", 7);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u6848\u4f8b 1\uff1a\u4f4d\u79fb/\u65cb\u8f6c");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result().table("tbl1").label("\u6848\u4f8b 1\uff1a\u4f4d\u79fb/\u65cb\u8f6c");
    model.result().numerical().create("pev2", "EvalPoint");
    model.result().numerical("pev2").label("\u6848\u4f8b 2\uff1a\u4f4d\u79fb/\u65cb\u8f6c");
    model.result().numerical("pev2").selection().set(2);
    model.result().numerical("pev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("pev2").set("expr", new String[]{"w"});
    model.result().numerical("pev2").set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf"});
    model.result().numerical("pev2").set("unit", new String[]{"m"});
    model.result().numerical("pev2").set("expr", new String[]{"w", "deltaZ_g"});
    model.result().numerical("pev2")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf", "\u91cd\u529b\u8f7d\u8377\u5f15\u8d77\u7684 Z \u4f4d\u79fb"});
    model.result().numerical("pev2").set("expr", new String[]{"w", "deltaZ_g", "thx"});
    model.result().numerical("pev2")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf", "\u91cd\u529b\u8f7d\u8377\u5f15\u8d77\u7684 Z \u4f4d\u79fb", "\u65cb\u8f6c\u573a\uff0cX \u5206\u91cf"});
    model.result().numerical("pev2").set("expr", new String[]{"w", "deltaZ_g", "thx", "thetaX_g"});
    model.result().numerical("pev2")
         .set("descr", new String[]{"\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf", "\u91cd\u529b\u8f7d\u8377\u5f15\u8d77\u7684 Z \u4f4d\u79fb", "\u65cb\u8f6c\u573a\uff0cX \u5206\u91cf", "\u91cd\u529b\u8f7d\u8377\u5f15\u8d77\u7684\u626d\u8f6c"});
    model.result().numerical("pev2").setIndex("descr", "delta_z \u8ba1\u7b97\u503c", 0);
    model.result().numerical("pev2").setIndex("descr", "delta_z \u89e3\u6790\u503c", 1);
    model.result().numerical("pev2").setIndex("descr", "theta_x \u8ba1\u7b97\u503c", 2);
    model.result().numerical("pev2").setIndex("descr", "theta_x \u89e3\u6790\u503c", 3);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u6848\u4f8b 2\uff1a\u4f4d\u79fb/\u65cb\u8f6c");
    model.result().numerical("pev2").set("table", "tbl2");
    model.result().numerical("pev2").setResult();
    model.result().table("tbl2").label("\u6848\u4f8b 2\uff1a\u4f4d\u79fb/\u65cb\u8f6c");
    model.result().numerical().create("pev3", "EvalPoint");
    model.result().numerical("pev3").selection().set(2);
    model.result().numerical("pev3").setIndex("looplevelinput", "first", 0);
    model.result().numerical("pev3").label("\u6765\u81ea Fx \u7684\u8f74\u5411\u5e94\u529b");
    model.result().numerical("pev3").set("expr", new String[]{"beam.s1"});
    model.result().numerical("pev3")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b"});
    model.result().numerical("pev3").set("unit", new String[]{"MPa"});
    model.result().numerical("pev3").set("expr", new String[]{"beam.s1", "beam.s2"});
    model.result().numerical("pev3")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b", "\u7b2c\u4e8c\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b"});
    model.result().numerical("pev3").set("expr", new String[]{"beam.s1", "beam.s2", "beam.s3"});
    model.result().numerical("pev3")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b", "\u7b2c\u4e8c\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b", "\u7b2c\u4e09\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b"});
    model.result().numerical("pev3").set("expr", new String[]{"beam.s1", "beam.s2", "beam.s3", "beam.s4"});
    model.result().numerical("pev3")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b", "\u7b2c\u4e8c\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b", "\u7b2c\u4e09\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b", "\u7b2c\u56db\u8ba1\u7b97\u70b9\u7684\u6b63\u5e94\u529b"});
    model.result().numerical("pev3").setIndex("descr", "\u7b2c\u4e00\u70b9", 0);
    model.result().numerical("pev3").setIndex("descr", "\u7b2c\u4e8c\u70b9", 1);
    model.result().numerical("pev3").setIndex("descr", "\u7b2c\u4e09\u70b9", 2);
    model.result().numerical("pev3").setIndex("descr", "\u7b2c\u56db\u70b9", 3);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u6765\u81ea Fx \u7684\u8f74\u5411\u5e94\u529b");
    model.result().numerical("pev3").set("table", "tbl3");
    model.result().numerical("pev3").setResult();
    model.result().table("tbl3").label("\u6765\u81ea Fx \u7684\u6b63\u5e94\u529b");
    model.result().numerical().create("pev4", "EvalPoint");
    model.result().numerical("pev4").label("\u603b\u5f2f\u66f2\u5e94\u529b");
    model.result().numerical("pev4").setIndex("looplevelinput", "first", 0);
    model.result().numerical("pev4").selection().set(1);
    model.result().numerical("pev4").set("expr", new String[]{"beam.sb1"});
    model.result().numerical("pev4")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b"});
    model.result().numerical("pev4").set("unit", new String[]{"MPa"});
    model.result().numerical("pev4").set("expr", new String[]{"beam.sb1", "sigmabx(y, z)"});
    model.result().numerical("pev4")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx"});
    model.result().numerical("pev4").set("expr", new String[]{"beam.sb1", "sigmabx(y, z)", "beam.sb2"});
    model.result().numerical("pev4")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e8c\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b"});
    model.result().numerical("pev4")
         .set("expr", new String[]{"beam.sb1", "sigmabx(y, z)", "beam.sb2", "sigmabx(y, z)"});
    model.result().numerical("pev4")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e8c\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx"});
    model.result().numerical("pev4")
         .set("expr", new String[]{"beam.sb1", "sigmabx(y, z)", "beam.sb2", "sigmabx(y, z)", "beam.sb3"});
    model.result().numerical("pev4")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e8c\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e09\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b"});
    model.result().numerical("pev4")
         .set("expr", new String[]{"beam.sb1", "sigmabx(y, z)", "beam.sb2", "sigmabx(y, z)", "beam.sb3", "sigmabx(y, z)"});
    model.result().numerical("pev4")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e8c\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e09\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx"});
    model.result().numerical("pev4")
         .set("expr", new String[]{"beam.sb1", "sigmabx(y, z)", "beam.sb2", "sigmabx(y, z)", "beam.sb3", "sigmabx(y, z)", "beam.sb4"});
    model.result().numerical("pev4")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e8c\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e09\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u56db\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b"});
    model.result().numerical("pev4")
         .set("expr", new String[]{"beam.sb1", "sigmabx(y, z)", "beam.sb2", "sigmabx(y, z)", "beam.sb3", "sigmabx(y, z)", "beam.sb4", "sigmabx(y, z)"});
    model.result().numerical("pev4")
         .set("descr", new String[]{"\u7b2c\u4e00\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e8c\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u4e09\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx", "\u7b2c\u56db\u8ba1\u7b97\u70b9\u5f2f\u66f2\u5e94\u529b", "sigmabx"});
    model.result().numerical("pev4").setIndex("descr", "\u7b2c\u4e00\u70b9\uff0c\u8ba1\u7b97\u503c", 0);
    model.result().numerical("pev4").setIndex("expr", "sigmabx(y1, z1)", 1);
    model.result().numerical("pev4").setIndex("descr", "\u7b2c\u4e00\u70b9\uff0c\u89e3\u6790\u503c", 1);
    model.result().numerical("pev4").setIndex("descr", "\u7b2c\u4e8c\u70b9\uff0c\u8ba1\u7b97\u503c", 2);
    model.result().numerical("pev4").setIndex("expr", "sigmabx(y2, z2)", 3);
    model.result().numerical("pev4").setIndex("descr", "\u7b2c\u4e8c\u70b9\uff0c\u89e3\u6790\u503c", 3);
    model.result().numerical("pev4").setIndex("descr", "\u7b2c\u4e09\u70b9\uff0c\u8ba1\u7b97\u503c", 4);
    model.result().numerical("pev4").setIndex("expr", "sigmabx(y3, z3)", 5);
    model.result().numerical("pev4").setIndex("descr", "\u7b2c\u4e09\u70b9\uff0c\u89e3\u6790\u503c", 5);
    model.result().numerical("pev4").setIndex("descr", "\u7b2c\u56db\u70b9\uff0c\u8ba1\u7b97\u503c", 6);
    model.result().numerical("pev4").setIndex("expr", "sigmabx(y4, z4)", 7);
    model.result().numerical("pev4").setIndex("descr", "\u7b2c\u56db\u70b9\uff0c\u89e3\u6790\u503c", 7);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u603b\u5f2f\u66f2\u5e94\u529b");
    model.result().numerical("pev4").set("table", "tbl4");
    model.result().numerical("pev4").setResult();
    model.result().table("tbl4").label("\u603b\u5f2f\u66f2\u5e94\u529b");
    model.result().numerical().create("pev5", "EvalPoint");
    model.result().numerical("pev5").label("\u526a\u5207\u5e94\u529b");
    model.result().numerical("pev5").setIndex("looplevelinput", "first", 0);
    model.result().numerical("pev5").selection().set(1);
    model.result().numerical("pev5").set("expr", new String[]{"beam.tsymax"});
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411"});
    model.result().numerical("pev5").set("unit", new String[]{"MPa"});
    model.result().numerical("pev5").set("expr", new String[]{"beam.tsymax", "tausy_max"});
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b"});
    model.result().numerical("pev5").set("expr", new String[]{"beam.tsymax", "tausy_max", "beam.tszmax"});
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b z \u65b9\u5411"});
    model.result().numerical("pev5")
         .set("expr", new String[]{"beam.tsymax", "tausy_max", "beam.tszmax", "tausz_max"});
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b z \u65b9\u5411", "z \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b"});
    model.result().numerical("pev5")
         .set("expr", new String[]{"beam.tsymax", "tausy_max", "beam.tszmax", "tausz_max", "beam.ttmax"});
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b z \u65b9\u5411", "z \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u6700\u5927\u626d\u8f6c\u526a\u5207\u5e94\u529b"});
    model.result().numerical("pev5")
         .set("expr", new String[]{"beam.tsymax", "tausy_max", "beam.tszmax", "tausz_max", "beam.ttmax", "taut_max"});

    return model;
  }

  public static Model run2(Model model) {
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b z \u65b9\u5411", "z \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u6700\u5927\u626d\u8f6c\u526a\u5207\u5e94\u529b", "\u626d\u8f6c\u5f15\u8d77\u7684\u526a\u5207\u5e94\u529b"});
    model.result().numerical("pev5")
         .set("expr", new String[]{"beam.tsymax", "tausy_max", "beam.tszmax", "tausz_max", "beam.ttmax", "taut_max", "beam.txymax"});
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b z \u65b9\u5411", "z \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u6700\u5927\u626d\u8f6c\u526a\u5207\u5e94\u529b", "\u626d\u8f6c\u5f15\u8d77\u7684\u526a\u5207\u5e94\u529b", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u65b9\u5411"});
    model.result().numerical("pev5")
         .set("expr", new String[]{"beam.tsymax", "tausy_max", "beam.tszmax", "tausz_max", "beam.ttmax", "taut_max", "beam.txymax", "tauxy_max"});
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b z \u65b9\u5411", "z \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u6700\u5927\u626d\u8f6c\u526a\u5207\u5e94\u529b", "\u626d\u8f6c\u5f15\u8d77\u7684\u526a\u5207\u5e94\u529b", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u65b9\u5411", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u5206\u91cf"});
    model.result().numerical("pev5")
         .set("expr", new String[]{"beam.tsymax", "tausy_max", "beam.tszmax", "tausz_max", "beam.ttmax", "taut_max", "beam.txymax", "tauxy_max", "beam.txzmax"});
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b z \u65b9\u5411", "z \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u6700\u5927\u626d\u8f6c\u526a\u5207\u5e94\u529b", "\u626d\u8f6c\u5f15\u8d77\u7684\u526a\u5207\u5e94\u529b", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u65b9\u5411", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u5206\u91cf", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cz \u65b9\u5411"});
    model.result().numerical("pev5")
         .set("expr", new String[]{"beam.tsymax", "tausy_max", "beam.tszmax", "tausz_max", "beam.ttmax", "taut_max", "beam.txymax", "tauxy_max", "beam.txzmax", "tauxz_max"});
    model.result().numerical("pev5")
         .set("descr", new String[]{"\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b y \u65b9\u5411", "y \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b z \u65b9\u5411", "z \u5411\u529b\u4ea7\u751f\u7684\u6700\u5927\u526a\u5207\u5e94\u529b", "\u6700\u5927\u626d\u8f6c\u526a\u5207\u5e94\u529b", "\u626d\u8f6c\u5f15\u8d77\u7684\u526a\u5207\u5e94\u529b", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u65b9\u5411", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u5206\u91cf", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cz \u65b9\u5411", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cz \u5206\u91cf"});
    model.result().numerical("pev5")
         .setIndex("descr", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u65b9\u5411\uff08\u8ba1\u7b97\u503c\uff09", 0);
    model.result().numerical("pev5")
         .setIndex("descr", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u65b9\u5411\uff08\u89e3\u6790\u503c\uff09", 1);
    model.result().numerical("pev5")
         .setIndex("descr", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cz \u65b9\u5411\uff08\u8ba1\u7b97\u503c\uff09", 2);
    model.result().numerical("pev5")
         .setIndex("descr", "\u526a\u529b\u7684\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cz \u65b9\u5411\uff08\u89e3\u6790\u503c\uff09", 3);
    model.result().numerical("pev5")
         .setIndex("descr", "\u6700\u5927\u626d\u8f6c\u526a\u5207\u5e94\u529b\uff08\u8ba1\u7b97\u503c\uff09", 4);
    model.result().numerical("pev5")
         .setIndex("descr", "\u6700\u5927\u626d\u8f6c\u526a\u5207\u5e94\u529b\uff08\u89e3\u6790\u503c\uff09", 5);
    model.result().numerical("pev5")
         .setIndex("descr", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u65b9\u5411\uff08\u8ba1\u7b97\u503c\uff09", 6);
    model.result().numerical("pev5")
         .setIndex("descr", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cy \u65b9\u5411\uff08\u89e3\u6790\u503c\uff09", 7);
    model.result().numerical("pev5")
         .setIndex("descr", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cz \u65b9\u5411\uff08\u8ba1\u7b97\u503c\uff09", 8);
    model.result().numerical("pev5")
         .setIndex("descr", "\u6700\u5927\u526a\u5207\u5e94\u529b\uff0cz \u65b9\u5411\uff08\u89e3\u6790\u503c\uff09", 9);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u526a\u5207\u5e94\u529b");
    model.result().numerical("pev5").set("table", "tbl5");
    model.result().numerical("pev5").setResult();
    model.result().table("tbl5").label("\u526a\u5207\u5e94\u529b");

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/beam", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.study("std2").label("\u7279\u5f81\u9891\u7387\u7814\u7a76\uff1a\u6881");
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 20);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"beam.disp"});
    model.result("pg2").feature("line1").set("threshold", "manual");
    model.result("pg2").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("line1").set("colortable", "Rainbow");
    model.result("pg2").feature("line1").set("colortabletrans", "none");
    model.result("pg2").feature("line1").set("colorscalemode", "linear");
    model.result("pg2").label("\u632f\u578b (beam)");
    model.result("pg2").feature("line1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "beam.re");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 1);
    model.result("pg2").feature("line1").set("tubeendcaps", false);
    model.result("pg2").feature("line1").create("def", "Deform");
    model.result("pg2").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7279\u5f81\u9891\u7387\u7814\u7a76\uff1a\u6881)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result().evaluationGroup().create("std2mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std2mpf1").set("data", "dset2");
    model.result().evaluationGroup("std2mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7279\u5f81\u9891\u7387\u7814\u7a76\uff1a\u6881)");
    model.result().evaluationGroup("std2mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std2mpf1").run();
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{2});
    model.result("pg2").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().numerical().create("pev6", "EvalPoint");
    model.result().numerical("pev6").label("\u622a\u9762\u529b");
    model.result().numerical("pev6").set("data", "cpt1");
    model.result().numerical("pev6").setIndex("expr", "beam.Nxl", 0);
    model.result().numerical("pev6").setIndex("descr", "N", 0);
    model.result().numerical("pev6").setIndex("expr", "beam.Mzl", 1);
    model.result().numerical("pev6").setIndex("descr", "M1", 1);
    model.result().numerical("pev6").setIndex("expr", "beam.Tyl", 2);
    model.result().numerical("pev6").setIndex("descr", "T2", 2);
    model.result().numerical("pev6").setIndex("expr", "beam.Myl", 3);
    model.result().numerical("pev6").setIndex("descr", "M2", 3);
    model.result().numerical("pev6").setIndex("expr", "beam.Tzl", 4);
    model.result().numerical("pev6").setIndex("descr", "T1", 4);
    model.result().numerical("pev6").setIndex("expr", "beam.Mxl", 5);
    model.result().numerical("pev6").setIndex("descr", "Mt", 5);
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").comments("\u622a\u9762\u529b");
    model.result().numerical("pev6").set("table", "tbl6");
    model.result().numerical("pev6").setResult();
    model.result().table("tbl6").label("\u622a\u9762\u529b");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").physics().create("bcs", "BeamCrossSection", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/bcs", false);
    model.study("std2").feature("eig").setSolveFor("/physics/bcs", false);

    model.component("comp2").geom("geom2").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/beam", false);
    model.study("std3").feature("stat").setSolveFor("/physics/bcs", true);
    model.study("std3").label("\u7a33\u6001\u7814\u7a76\uff1a\u6881\u6a2a\u622a\u9762");

    model.geom()
         .load(new String[]{"part1"}, "Structural_Mechanics_Module\\Beams\\Generic\\C_beam_generic.mph", new String[]{"part1"});
    model.component("comp2").geom("geom2").create("pi1", "PartInstance");
    model.component("comp2").geom("geom2").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom2").feature("pi1").set("part", "part1");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "d", "h2");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "b", "h1");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "tw", "t1");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "tf", "t2");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "r1", "2[mm]");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "r2", 0);
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "slope", 0);
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "u", 0);
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").physics("bcs").feature("hcs1").set("E_mat", "userdef");
    model.component("comp2").physics("bcs").feature("hcs1").set("nu_mat", "userdef");

//    Started method call EvaluateSectionForces 1

    model.result().dataset("cpt1").set("pointx", "0");

    model.component("comp2").physics("bcs").feature("hcs1").set("N", "10000.000000000016");
    model.component("comp2").physics("bcs").feature("hcs1").set("M1", "99.99999999981353");
    model.component("comp2").physics("bcs").feature("hcs1").set("T2", "99.99999999978343");
    model.component("comp2").physics("bcs").feature("hcs1").set("M2", "49.99999999995472");
    model.component("comp2").physics("bcs").feature("hcs1").set("T1", "-49.999999999933834");
    model.component("comp2").physics("bcs").feature("hcs1").set("Mt", "-9.99999999999996");

//    Finished method call EvaluateSectionForces 1

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u5f2f\u77e9 M1 (bcs)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "bcs.sM1");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u526a\u529b T2 (bcs)");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "bcs.restT2");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("solutionparams", "parent");
    model.result("pg4").feature("arws1").set("expr", new String[]{"bcs.tT2x", "bcs.tT2y"});
    model.result("pg4").feature("arws1").set("xnumber", 60);
    model.result("pg4").feature("arws1").set("ynumber", 60);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u5f2f\u77e9 M2 (bcs)");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "bcs.sM2");
    model.result("pg5").feature("surf1").set("colortable", "Wave");
    model.result("pg5").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u526a\u529b T1 (bcs)");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "bcs.restT1");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature().create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("showsolutionparams", "on");
    model.result("pg6").feature("arws1").set("solutionparams", "parent");
    model.result("pg6").feature("arws1").set("expr", new String[]{"bcs.tT1x", "bcs.tT1y"});
    model.result("pg6").feature("arws1").set("xnumber", 60);
    model.result("pg6").feature("arws1").set("ynumber", 60);
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").feature("arws1").set("showsolutionparams", "on");
    model.result("pg6").feature("arws1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u626d\u8f6c (bcs)");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "bcs.restMt");
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature().create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("showsolutionparams", "on");
    model.result("pg7").feature("arws1").set("solutionparams", "parent");
    model.result("pg7").feature("arws1").set("expr", new String[]{"bcs.tMtx", "bcs.tMty"});
    model.result("pg7").feature("arws1").set("xnumber", 60);
    model.result("pg7").feature("arws1").set("ynumber", 60);
    model.result("pg7").feature("arws1").set("color", "black");
    model.result("pg7").feature("arws1").set("showsolutionparams", "on");
    model.result("pg7").feature("arws1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u7b49\u6548\u5e94\u529b (bcs)");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("solutionparams", "parent");
    model.result("pg8").feature("surf1").set("expr", "bcs.mises");
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset4");
    model.result().evaluationGroup("eg1").label("\u622a\u9762\u5c5e\u6027 (bcs)");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("\u5747\u5300\u6a2a\u622a\u9762 1");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.A", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u9762\u79ef", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.I1", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6700\u5927\u4e3b\u60ef\u6027\u77e9", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.ei1", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u526a\u5207\u4e2d\u5fc3\u4f4d\u7f6e\uff0c\u4e3b\u8f74\u7cfb\uff0c1 \u5206\u91cf", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.mu2", 3);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6700\u5927\u526a\u5207\u5e94\u529b\u56e0\u5b50\uff0c\u4e3b\u8f74\u7cfb\uff0c2 \u5206\u91cf", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.kappa2", 4);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u526a\u5207\u6821\u6b63\u56e0\u5b50\uff0c\u4e3b\u8f74\u7cfb\uff0c2 \u5206\u91cf", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.I2", 5);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6700\u5c0f\u4e3b\u60ef\u6027\u77e9", 5);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.ei2", 6);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u526a\u5207\u4e2d\u5fc3\u4f4d\u7f6e\uff0c\u4e3b\u8f74\u7cfb\uff0c2 \u5206\u91cf", 6);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.mu1", 7);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6700\u5927\u526a\u5207\u5e94\u529b\u56e0\u5b50\uff0c\u4e3b\u8f74\u7cfb\uff0c1 \u5206\u91cf", 7);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.kappa1", 8);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u526a\u5207\u6821\u6b63\u56e0\u5b50\uff0c\u4e3b\u8f74\u7cfb\uff0c1 \u5206\u91cf", 8);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.J", 9);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u626d\u8f6c\u5e38\u6570", 9);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.Wt", 10);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u6297\u626d\u622a\u9762\u6a21\u91cf", 10);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.alpha", 11);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u7b2c\u4e00\u5c40\u90e8\u8f74\u4e0e\u7b2c\u4e00\u4e3b\u8f74\u7684\u5939\u89d2", 11);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.h1", 12);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u7b2c\u4e00\u4e3b\u65b9\u5411\u7684\u622a\u9762\u9ad8\u5ea6", 12);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp2.bcs.hcs1.h2", 13);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u7b2c\u4e8c\u4e3b\u65b9\u5411\u7684\u622a\u9762\u9ad8\u5ea6", 13);
    model.result().evaluationGroup("eg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").run();

    model.component("comp1").physics("beam").create("csd2", "CrossSectionBeam", 1);
    model.component("comp1").physics("beam").feature("csd2").selection().set(1);
    model.component("comp1").physics("beam").feature("csd2").set("area", "comp2.bcs.hcs1.A");
    model.component("comp1").physics("beam").feature("csd2").set("Izz", "comp2.bcs.hcs1.I1");
    model.component("comp1").physics("beam").feature("csd2").set("ez", "comp2.bcs.hcs1.ei1");
    model.component("comp1").physics("beam").feature("csd2").set("Iyy", "comp2.bcs.hcs1.I2");
    model.component("comp1").physics("beam").feature("csd2").set("ey", "comp2.bcs.hcs1.ei2");
    model.component("comp1").physics("beam").feature("csd2").set("J_beam", "comp2.bcs.hcs1.J");
    model.component("comp1").physics("beam").feature("csd2").set("hy", "comp2.bcs.hcs1.h2");
    model.component("comp1").physics("beam").feature("csd2").set("hz", "comp2.bcs.hcs1.h1");
    model.component("comp1").physics("beam").feature("csd2").set("Wt", "comp2.bcs.hcs1.Wt");
    model.component("comp1").physics("beam").feature("csd2").set("muy", "comp2.bcs.hcs1.mu2");
    model.component("comp1").physics("beam").feature("csd2").set("muz", "comp2.bcs.hcs1.mu1");
    model.component("comp1").physics("beam").feature("csd2").feature("so1").set("point_beam", new int[]{0, 0, 1});

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/beam", true);
    model.study("std4").feature("stat").setSolveFor("/physics/bcs", false);
    model.study("std4").setGenPlots(false);
    model.study("std4")
         .label("\u7a33\u6001\u7814\u7a76\uff1a\u6881\uff08\u6765\u81ea\u201c\u6881\u6a2a\u622a\u9762\u201d\u7684\u8f93\u5165\uff09");
    model.study("std4").feature("stat").set("usesol", true);
    model.study("std4").feature("stat").set("notsolmethod", "sol");
    model.study("std4").feature("stat").set("notstudy", "std3");
    model.study("std4").feature("stat").set("useloadcase", true);
    model.study("std4").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std4").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std4").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std4").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std4").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std4").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std4").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std4").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std4").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std4").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);

    return model;
  }

  public static Model run3(Model model) {
    model.study("std4").feature("stat").setIndex("loadcase", "\u70b9\u8f7d\u8377", 0);
    model.study("std4").feature("stat").setIndex("loadgroup", true, 0, 1);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().numerical().create("pev7", "EvalPoint");
    model.result().numerical("pev7").label("von Mises \u5e94\u529b");
    model.result().numerical("pev7").setIndex("looplevelinput", "first", 0);
    model.result().numerical("pev7").selection().set(1);
    model.result().numerical("pev7").set("expr", new String[]{"beam.mises"});
    model.result().numerical("pev7").set("descr", new String[]{"von Mises \u5e94\u529b"});
    model.result().numerical("pev7").set("unit", new String[]{"MPa"});
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").comments("von Mises \u5e94\u529b");
    model.result().numerical("pev7").set("table", "tbl7");
    model.result().numerical("pev7").setResult();
    model.result().numerical("pev7").set("data", "dset5");
    model.result().numerical("pev7").set("table", "tbl7");
    model.result().numerical("pev7").appendResult();
    model.result().table("tbl7").label("von Mises \u5e94\u529b");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"beam/csd2"});
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"beam/csd2"});

    model.result("pg8").run();

    model.title("\u69fd\u5f62\u6881");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u7684\u201c\u6881\u201d\u63a5\u53e3\u3002\u5176\u4e2d\u8ba1\u7b97\u60ac\u81c2\u6881\u7684\u53d8\u5f62\u3001\u622a\u9762\u529b\u3001\u5e94\u529b\u4ee5\u53ca\u7279\u5f81\u9891\u7387\u6570\uff0c\u8fd8\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u6881\u6a2a\u622a\u9762\u201d\u63a5\u53e3\u6765\u8ba1\u7b97\u5e94\u529b\u548c\u6881\u6a2a\u622a\u9762\u6570\u636e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("channel_beam.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
