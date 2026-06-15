/*
 * magnetic_brake.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class magnetic_brake {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Transducers_and_Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mef", "ElectricInductionCurrents", "geom1");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mef", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ge", true);

    model.param().set("dt", "1[cm]");
    model.param().descr("dt", "\u5706\u76d8\u539a\u5ea6");
    model.param().set("dr", "10[cm]");
    model.param().descr("dr", "\u5706\u76d8\u534a\u5f84");
    model.param().set("mh", "12[cm]");
    model.param().descr("mh", "\u78c1\u4f53\u9ad8\u5ea6");
    model.param().set("mw", "2[cm]");
    model.param().descr("mw", "\u78c1\u4f53\u5bbd\u5ea6");
    model.param().set("ml", "8[cm]");
    model.param().descr("ml", "\u78c1\u4f53\u957f\u5ea6");
    model.param().set("mt", "2[cm]");
    model.param().descr("mt", "\u78c1\u94c1\u539a\u5ea6");
    model.param().set("mg", "1.5[cm]");
    model.param().descr("mg", "\u78c1\u4f53\u6c14\u9699");
    model.param().set("mB", "1[T]");
    model.param().descr("mB", "\u78c1\u4f53\u901a\u91cf");
    model.param().set("ymur", "4000");
    model.param().descr("ymur", "\u8f6d\u76f8\u5bf9\u78c1\u5bfc\u7387");
    model.param().set("dV0", "1000[rpm]");
    model.param().descr("dV0", "\u5706\u76d8\u521d\u59cb\u89d2\u9891\u7387");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "dr*3");
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u5706\u76d8");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "dr");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "dt");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-dt/2"});
    model.component("comp1").geom("geom1").feature("cyl1").set("selresult", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("quickx", "-mt/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"mw", "mh-2*mw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"dr+ml/2-mw/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"ml-mw", "mw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"dr-ml/2+mw", "mh/2-mw"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"mw", "mh/2-mg/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"dr-ml/2", "mg/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("r2", "r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "mt", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").add("sph1", 1, 2, 3, 4, 5, 6, 7, 8);

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intdisc");
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").label("\u5bf9\u5706\u76d8\u6c42\u79ef\u5206");

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").selection().set();
    model.component("comp1").massProp("mass1").selection().named("geom1_cyl1_dom");
    model.component("comp1").massProp("mass1").set("outputFrame", "spatial");
    model.component("comp1").massProp("mass1").set("expr", "mat1.def.rho");
    model.component("comp1").massProp("mass1").set("createVolume", false);
    model.component("comp1").massProp("mass1").set("createMass", false);
    model.component("comp1").massProp("mass1").set("createCenterOfMass", false);
    model.component("comp1").massProp("mass1").set("createPrincipalInertia", false);

    model.component("comp1").physics("mef").create("alcs1", "ElectromagneticModelSolid", 3);
    model.component("comp1").physics("mef").feature("alcs1").selection().set(3, 4, 5, 6);
    model.component("comp1").physics("mef").create("alcs2", "ElectromagneticModelSolid", 3);
    model.component("comp1").physics("mef").feature("alcs2").selection().set(7);
    model.component("comp1").physics("mef").feature("alcs2").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mef").feature("alcs2")
         .set("e_crel_BH_RemanentFluxDensity", new int[]{0, 0, 1});
    model.component("comp1").physics("mef").feature("alcs2").label("\u6c38\u78c1\u4f53");
    model.component("comp1").physics("mef").create("alcs3", "ElectromagneticModelSolid", 3);
    model.component("comp1").physics("mef").feature("alcs3").selection().named("geom1_cyl1_dom");
    model.component("comp1").physics("mef").feature("alcs3").label("\u5706\u76d8");
    model.component("comp1").physics("mef").create("vlt1", "Velocity", 3);
    model.component("comp1").physics("mef").feature("vlt1").selection().named("geom1_cyl1_dom");
    model.component("comp1").physics("mef").feature("vlt1").set("v", new String[]{"-y*W", "x*W", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("geom1_cyl1_dom");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(3, 4, 5, 6);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"ymur"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat2").label("\u8f6d");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat3").label("N50 (Sintered NdFeB)");
    model.component("comp1").material("mat3").set("family", "chrome");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").set("normBr", "1.41[T]");
    model.component("comp1").material("mat3").selection().set(7);
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").set("murec", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").set("normBr", new String[]{"mB"});
    model.component("comp1").material("mat3").label("\u901a\u7528\u78c1\u4f53");

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "W", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "Wt-intdisc(x*mef.FLtzy-y*mef.FLtzx)/mass1.Izz", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "2*pi*dV0", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u89d2\u901f\u5ea6", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "angularfrequency");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "angularacceleration");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("axialTorque", "-intdisc(x*mef.FLtzy-y*mef.FLtzx)");
    model.component("comp1").variable("var1").descr("axialTorque", "\u8f74\u5411\u626d\u77e9");
    model.component("comp1").variable("var1").set("totalHeating", "intdisc(mef.Qh)");
    model.component("comp1").variable("var1").descr("totalHeating", "\u603b\u70ed\u91cf");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("window", "new");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("expr", "axialTorque");
    model.component("comp1").probe("var2").set("descr", "\u8f74\u5411\u626d\u77e9");
    model.component("comp1").probe("var2").set("window", "new");
    model.component("comp1").probe().create("var3", "GlobalVariable");
    model.component("comp1").probe("var3").set("expr", "totalHeating");
    model.component("comp1").probe("var3").set("descr", "\u603b\u70ed\u91cf");
    model.component("comp1").probe("var3").set("window", "new");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("zscale", 1.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/ge", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.5,25)");
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").setEntry("atolmethod", "comp1_ODE1", "unscaled");
    model.sol("sol1").feature("t1").setEntry("atolvaluemethod", "comp1_ODE1", "manual");
    model.sol("sol1").feature("t1").setEntry("atol", "comp1_ODE1", "0.1");
    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol1").feature("t1").feature("i1").set("rhob", 1);

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u89d2\u901f\u5ea6 vs. \u65f6\u95f4");
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").label("\u8f74\u5411\u626d\u77e9 vs. \u65f6\u95f4");
    model.result("pg3").set("window", "window3");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg3").run();
    model.result("pg3").label("\u603b\u70ed\u91cf vs. \u65f6\u95f4");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "mef.normJ");
    model.result("pg4").feature("vol1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg4").feature("vol1").create("sel1", "Selection");
    model.result("pg4").feature("vol1").feature("sel1").selection().set(2);
    model.result("pg4").run();
    model.result("pg4").create("vol2", "Volume");
    model.result("pg4").feature("vol2").set("expr", "1");
    model.result("pg4").feature("vol2").set("coloring", "uniform");
    model.result("pg4").feature("vol2").set("color", "gray");
    model.result("pg4").feature("vol2").create("sel1", "Selection");
    model.result("pg4").feature("vol2").feature("sel1").selection().set(3, 4, 5, 6, 7);
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"mef.Jx", "mef.Jy", "mef.Jz"});
    model.result("pg4").feature("arws1").set("descr", "\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").feature("arws1").set("arrowcount", 1000);
    model.result("pg4").feature("arws1").set("color", "white");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 51, 0);
    model.result("pg4").run();

    model.title("\u7535\u78c1\u5236\u52a8\u5668");

    model
         .description("\u4e00\u4e2a\u91d1\u5c5e\u78c1\u76d8\u5728\u78c1\u4f53\u7684\u7a7a\u6c14\u7f1d\u9699\u4e2d\u65cb\u8f6c\u3002\u7531\u611f\u5e94\u7535\u6d41\u4ea7\u751f\u7684\u6d1b\u4f26\u5179\u529b (JxB) \u5f62\u6210\u4e86\u4e00\u4e2a\u5236\u52a8\u626d\u77e9\uff0c\u4f7f\u78c1\u76d8\u7684\u8f6c\u52a8\u51cf\u6162\u3002\u8be5\u4e09\u7ef4\u6a21\u578b\u8ba1\u7b97\u6da1\u6d41\u548c\u6d1b\u4f26\u5179\u529b\u5206\u5e03\uff0c\u540c\u65f6\u6c42\u89e3\u4e86\u8026\u5408\u89d2\u901f\u5ea6\u968f\u65f6\u95f4\u6f14\u5316\u7684\u5e38\u5fae\u5206\u65b9\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("magnetic_brake.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
