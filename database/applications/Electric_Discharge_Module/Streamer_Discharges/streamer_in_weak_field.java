/*
 * streamer_in_weak_field.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:51 by COMSOL 6.3.0.290. */
public class streamer_in_weak_field {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Streamer_Discharges");

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

    model.param().set("V0", "13[kV]");
    model.param().descr("V0", "Applied voltage");
    model.param().set("De", "1800[cm^2/s]");
    model.param().descr("De", "Electron diffusion coefficient");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{5, 6});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.2, 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", "pi*0.45");
    model.component("comp1").geom("geom1").feature("pc1").set("coord", new String[]{"0.18*tan(s)", ""});
    model.component("comp1").geom("geom1").feature("pc1").setIndex("coord", "1/cos(s)", 1);
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("pc1", 2);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("r1", 2);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("ls1", 2);
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex2").set("pc1", 1);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("ls1", "ls2", "pc1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("csol1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord1", new String[]{"0", "1-1/1000"});
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"0.2", "1-1/1000"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("edis").feature("gas1").set("DiffusionCoefficientMethod", "UserDefined");
    model.component("comp1").physics("edis").feature("gas1")
         .set("D_e", new String[]{"De", "0", "0", "0", "De", "0", "0", "0", "De"});
    model.component("comp1").physics("edis").feature("gas1").set("D_p", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp1").physics("edis").feature("gas1").set("D_n", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").info().create("matinfo1");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChargeTransportGases", "ChargeTransportGases", "Charge transport in gases");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Air [Kulikovsky, 1998]");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").set("phase", "gas");
    model.component("comp1").material("mat1").set("orientation", "Kulikovsky, 1998");
    model.component("comp1").material("mat1").set("info", new String[][]{{"matinfo1", "", ""}});
    model.component("comp1").material("mat1").info("matinfo1").label("Information 1");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .label("Charge transport in gases");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("funcname", "alphaFun");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("expr", "3430*exp(-660/x)");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("fununit", "cm^-1");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("argunit", new String[]{"Td"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an2")
         .set("funcname", "muFun");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an2")
         .set("expr", "1526.5*(x^-0.25)");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an2")
         .set("fununit", "cm^2/V/s");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an2")
         .set("argunit", new String[]{"Td"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an3")
         .set("funcname", "yitaFun");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an3")
         .set("expr", "14.7*exp(-100/x)+96*(x)^-1.1");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an3")
         .set("fununit", "cm^-1");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an3")
         .set("argunit", new String[]{"Td"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_e", new String[]{"muFun(EN)", "0", "0", "0", "muFun(EN)", "0", "0", "0", "muFun(EN)"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_p", new String[]{"2.34[cm^2/V/s]*1[atm]/pA", "0", "0", "0", "2.34[cm^2/V/s]*1[atm]/pA", "0", "0", "0", "2.34[cm^2/V/s]*1[atm]/pA"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_n", new String[]{"2.7[cm^2/V/s]*1[atm]/pA", "0", "0", "0", "2.7[cm^2/V/s]*1[atm]/pA", "0", "0", "0", "2.7[cm^2/V/s]*1[atm]/pA"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("alpha", "alphaFun(EN)");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("eta", "yitaFun(EN)");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("beta_ep", "5e-8[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("beta_pn", "2e-6[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("normE", "\u7535\u573a\u6a21");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("N", "\u4e2d\u6027\u6570\u5bc6\u5ea6");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("EN", "\u7ea6\u5316\u7535\u573a");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("electricfield");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("mu_p", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("mu_n", new String[]{"0"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("Ne0", "(1e6+1e14*exp(-(((z-0.975[cm])/(1[cm]))/0.025)^2-(r/(1[cm])/0.01)^2))[cm^-3]");
    model.component("comp1").variable("var1").descr("Ne0", "Initial electron density");
    model.component("comp1").variable("var1")
         .set("Nn0", "(1e6+1e11*exp(-(((z-0.975[cm])/(1[cm]))/0.025)^2-(r/(1[cm])/0.01)^2))[cm^-3]");
    model.component("comp1").variable("var1").descr("Nn0", "Initial negative ion density");
    model.component("comp1").variable("var1").set("Np0", "Ne0+Nn0");
    model.component("comp1").variable("var1").descr("Np0", "Initial positive ion density");

    model.component("comp1").physics("edis").feature("gas1").feature("init1").setIndex("n0_e", "Ne0", 0);
    model.component("comp1").physics("edis").feature("gas1").feature("init1").setIndex("n0_p", "Np0", 0);
    model.component("comp1").physics("edis").feature("gas1").feature("init1").setIndex("n0_n", "Nn0", 0);
    model.component("comp1").physics("edis").feature("gas1").create("ece1", "Electrode", 1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").selection().set(10, 11);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("V0", "V0");
    model.component("comp1").physics("edis").feature("gas1").create("ece2", "Electrode", 1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").selection().set(2, 6);
    model.component("comp1").physics("edis").feature("gas1").create("pi1", "Photoionization", 2);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 80);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 600);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1/150");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tunit", "ns");
    model.study("std1").feature("time").set("tlist", "0 range(1,2,23)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", 0.025);
    model.sol("sol1").feature("t1").feature("se1").set("segstabacc", "segaacc");
    model.sol("sol1").feature("t1").feature("se1").create("ss4", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature().move("ss4", 1);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_V", "comp1_edis_logn_e"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss4")
         .set("segvar", new String[]{"comp1_edis_logn_n", "comp1_edis_logn_p"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss4").set("subjtech", "once");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "edis.Ez");
    model.result("pg1").feature("lngr1").set("unit", "kV/cm");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "z");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerleft");
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", "edis.n_e");
    model.result("pg2").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "z");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").set("ylog", true);
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("ymin", "1e8");
    model.result("pg2").set("ymax", "1e15");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "edis.Sph");
    model.result("pg3").feature("surf1").set("unit", "1/(cm^3*s)");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().set(1, 2);
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u5f31\u7535\u573a\u4e2d\u7684\u6b63\u6d41\u6ce8\u4f20\u64ad");

    model
         .description("\u672c\u4eff\u771f\u91cd\u73b0\u6b63\u6d41\u6ce8\u5728\u6e29\u548c\u7535\u573a\u4e2d\u7684\u53d1\u5c55\u8fc7\u7a0b\uff0c\u5f3a\u8c03\u4e86\u5149\u81f4\u7535\u79bb\u5728\u6d41\u6ce8\u524d\u6cbf\u63d0\u4f9b\u79cd\u5b50\u7535\u5b50\u7684\u5173\u952e\u4f5c\u7528\u3002\u6a21\u62df\u7684\u7535\u573a\u548c\u7535\u5b50\u5bc6\u5ea6\u968f\u65f6\u95f4\u7684\u53d8\u5316\u60c5\u51b5\u4e0e\u5df2\u53d1\u8868\u6587\u732e\u4e2d\u7684\u8bb0\u5f55\u5bc6\u5207\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("streamer_in_weak_field.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
