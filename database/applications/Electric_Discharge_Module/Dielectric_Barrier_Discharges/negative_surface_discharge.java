/*
 * negative_surface_discharge.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:49 by COMSOL 6.3.0.290. */
public class negative_surface_discharge {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Dielectric_Barrier_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("edis", "ElectricDischarge", "geom1");

    model.study().create("std1");
    model.study("std1").create("esi", "ElectrostaticsInitialization");
    model.study("std1").feature("esi").set("solnum", "auto");
    model.study("std1").feature("esi").set("notsolnum", "auto");
    model.study("std1").feature("esi").set("outputmap", new String[]{});
    model.study("std1").feature("esi").set("ngenAUX", "1");
    model.study("std1").feature("esi").set("goalngenAUX", "1");
    model.study("std1").feature("esi").set("ngenAUX", "1");
    model.study("std1").feature("esi").set("goalngenAUX", "1");
    model.study("std1").feature("esi").setSolveFor("/physics/edis", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/edis", true);

    model.param().set("V0", "-2.5[kV]");
    model.param().descr("V0", "Applied voltage");
    model.param().set("Rcurv", "25[um]");
    model.param().descr("Rcurv", "Radius of curvature");
    model.param().set("a", "1/Rcurv/2*1[cm]");
    model.param().descr("a", "Parabola parameter in cm");
    model.param().set("d_air", "500[um]");
    model.param().descr("d_air", "Air gap");
    model.param().set("d_glass", "800[um]");
    model.param().descr("d_glass", "Thickness of the glass layer");
    model.param().set("d_BSO", "160[um]");
    model.param().descr("d_BSO", "Thickness of the BSO layer");
    model.param().set("gap", "d_air+d_BSO+d_glass");
    model.param().descr("gap", "Electrode gap");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "d_glass", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "d_BSO", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.03, 1});
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "60[um]", 0);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", 0.2);
    model.component("comp1").geom("geom1").feature("pc1").set("coord", new String[]{"s", "a*(s)^2*1[cm]+gap"});
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("pc1", 2);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("r1", 4);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("ls1", 2);
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex2").set("pc1", 1);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("ls1", "ls2", "pc1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("csol1");
    model.component("comp1").geom("geom1").feature().duplicate("pc2", "pc1");
    model.component("comp1").geom("geom1").feature("pc2").set("parmax", "60[um]");
    model.component("comp1").geom("geom1").feature("pc2")
         .set("coord", new String[]{"s", "a*(s)^2*1[cm]/1.2+gap-10[um]"});
    model.component("comp1").geom("geom1").feature().duplicate("pc3", "pc2");
    model.component("comp1").geom("geom1").feature("pc3")
         .set("coord", new String[]{"s", "a*(s)^2*1[cm]/1.5+gap-60[um]"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("edis").prop("Stabilization").set("StreamlineDiffusion", false);
    model.component("comp1").physics("edis").prop("Stabilization").set("IsotropicDiffusion", true);
    model.component("comp1").physics("edis").prop("Stabilization").set("delid", 0.15);
    model.component("comp1").physics("edis").prop("PhysicalModel").set("Solid", true);
    model.component("comp1").physics("edis").feature("solid1").selection().set(1, 2, 6, 7, 9, 10);
    model.component("comp1").physics("edis").feature("solid1").set("MaterialModel", "Insulator");
    model.component("comp1").physics("edis").feature("gas1").set("DiffusionCoefficientMethod", "UserDefined");
    model.component("comp1").physics("edis").feature("gas1")
         .set("D_e", new double[]{0.18, 0, 0, 0, 0.18, 0, 0, 0, 0.18});
    model.component("comp1").physics("edis").feature("gas1")
         .set("D_p", new double[]{0.01, 0, 0, 0, 0.01, 0, 0, 0, 0.01});
    model.component("comp1").physics("edis").feature("gas1")
         .set("D_n", new double[]{0.01, 0, 0, 0, 0.01, 0, 0, 0, 0.01});
    model.component("comp1").physics("edis").feature("gas1").feature("init1").setIndex("n0_e", "1E5[1/cm^3]", 0);
    model.component("comp1").physics("edis").feature("gas1").feature("init1").setIndex("n0_p", "1E5[1/cm^3]", 0);
    model.component("comp1").physics("edis").feature("gas1").feature("init1").setIndex("n0_n", "1E5[1/cm^3]", 0);
    model.component("comp1").physics("edis").feature("gas1").feature("dibt1").set("pBnd", "NoFlux");
    model.component("comp1").physics("edis").feature("gas1").create("ece1", "Electrode", 1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").selection().set(29, 30, 31);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("V0", "V0");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("eBnd", "SurfaceEmission");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("nBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("item.n0_n", "1E5[1/cm^3]");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("SecondaryElectronEmission", true);
    model.component("comp1").physics("edis").feature("solid1").create("ece1", "Electrode", 1);
    model.component("comp1").physics("edis").feature("solid1").feature("ece1").selection().set(2, 10, 18);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChargeTransportGases", "ChargeTransportGases", "Charge transport in gases");
    model.component("comp1").material("mat1").label("Air [Kang et al. 2003]");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .label("Charge transport in gases");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_e", new String[]{"6060*(normE/1[V/cm])^0.75*1[cm/s]/normE", "0", "0", "0", "6060*(normE/1[V/cm])^0.75*1[cm/s]/normE", "0", "0", "0", "6060*(normE/1[V/cm])^0.75*1[cm/s]/normE"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_p", new String[]{"2.34*(normE/1[V/cm])^1*1[cm/s]/normE", "0", "0", "0", "2.34*(normE/1[V/cm])^1*1[cm/s]/normE", "0", "0", "0", "2.34*(normE/1[V/cm])^1*1[cm/s]/normE"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_n", new String[]{"2.7*(normE/1[V/cm])^1*1[cm/s]/normE", "0", "0", "0", "2.7*(normE/1[V/cm])^1*1[cm/s]/normE", "0", "0", "0", "2.7*(normE/1[V/cm])^1*1[cm/s]/normE"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("alpha", "3500*exp(-1.65e5/(normE/1[V/cm]))*1[cm^-1]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("eta", "15*exp(-2.5e4/(normE/1[V/cm]))*1[cm^-1]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("beta_ep", "1e-7[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("beta_pn", "1e-7[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("normE", "Electric field norm");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("N", "Neutral number density");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("EN", "Reduced electric field");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("electricfield");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("pressure");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(2, 7, 10);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"56"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(1, 6, 9);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"3"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature().move("map1", 1);
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4, 5);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(27, 28, 29);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 150);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(7, 15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 150);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(8, 16);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(3, 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1/1000");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "1[um]");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(30, 31);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "i0");
    model.component("comp1").probe("var1").set("expr", "edis.I0_0");
    model.component("comp1").probe("var1").set("unit", "mA");

    model.study("std1").feature("time").set("tunit", "\u00b5s");
    model.study("std1").feature("time").set("tlist", "range(0,0.01,0.2) range(1,2,5) 10");
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("se1").set("segstabacc", "segaacc");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subtermconst", "tol");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subtermconst", "tol");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("xlog", true);
    model.result("pg1").set("legendpos", "lowerleft");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").setIndex("looplevel", new int[]{1, 2, 6, 11, 22, 24, 25}, 0);
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 0.1);
    model.result("pg2").set("ymax", 0.02);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("expr", "edis.rhos");
    model.result("pg2").feature("lngr1").set("unit", "nC/mm^2");
    model.result("pg2").feature("lngr1").selection().set(6, 14, 22);
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "r");
    model.result("pg2").feature("lngr1").set("linemarker", "cycle");
    model.result("pg2").feature("lngr1").set("markerpos", "interp");
    model.result("pg2").feature("lngr1").set("markers", 60);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "edis.Ez");
    model.result("pg3").feature("lngr1").set("unit", "kV/cm");
    model.result("pg3").feature("lngr1").selection().set(1, 3, 5, 7, 8);
    model.result("pg3").feature("lngr1").set("xdataexpr", "z");
    model.result("pg3").feature("lngr1").set("markers", 8);
    model.result("pg3").run();
    model.result("pg3").set("ymin", 0.1);
    model.result("pg3").set("ymax", 1200);
    model.result("pg3").set("ylog", true);
    model.result("pg3").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "edis.n_e");
    model.result("pg4").feature("surf1").set("unit", "1/cm^3");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").run();
    model.result("pg4").set("data", "mir1");
    model.result("pg4").set("view", "new");
    model.result("pg4").run();

    model.view("view2").axis().set("xmin", -0.06);
    model.view("view2").axis().set("xmax", 0.06);
    model.view("view2").axis().set("ymin", 0.09);
    model.view("view2").axis().set("ymax", 0.2);

    model.result("pg4").run();

    model.title("\u6c14-\u56fa\u754c\u9762\u4e0a\u7684\u8d1f\u8868\u9762\u653e\u7535");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u70b9\u5bf9\u677f\u7535\u6781\u914d\u7f6e\u4e0b\u7684\u8d1f\u4ecb\u8d28\u963b\u6321\u653e\u7535\u8fc7\u7a0b\uff0c\u5176\u4e2d\u5728\u6c14\u9699\u4e2d\u63d2\u5165\u4e24\u4e2a\u56fa\u4f53\u4ecb\u7535\u5c42\u3002\u5728\u9634\u6781\u65bd\u52a0 2.5 kV \u7684\u8d1f\u7535\u538b\uff0c\u6fc0\u53d1\u4e86\u7535\u6655\u6d41\u6ce8\u7684\u4f20\u64ad\uff0c\u4ece\u800c\u4ea7\u751f\u7535\u6d41\u8109\u51b2\u3002\u7531\u6b64\u4ea7\u751f\u7684\u8d1f\u8f7d\u6d41\u5b50\u5728\u6c14-\u56fa\u754c\u9762\u79ef\u7d2f\uff0c\u8fdb\u800c\u6539\u53d8\u7535\u573a\u5206\u5e03\u3002\u6700\u7ec8\uff0c\u5f62\u6210\u4e86\u7a33\u5b9a\u7684\u8d1f\u8868\u9762\u653e\u7535\u3002\n\n\u6a21\u62df\u5f97\u5230\u7684\u6c14-\u56fa\u754c\u9762\u7684\u653e\u7535\u7535\u6d41\u548c\u8868\u9762\u7535\u8377\u5206\u5e03\u4e0e\u5b9e\u9a8c\u7ed3\u679c\u975e\u5e38\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("negative_surface_discharge.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
