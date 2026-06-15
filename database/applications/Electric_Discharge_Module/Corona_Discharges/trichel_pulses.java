/*
 * trichel_pulses.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:49 by COMSOL 6.3.0.290. */
public class trichel_pulses {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Corona_Discharges");

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

    model.param().set("V0", "-5.5[kV]");
    model.param().descr("V0", "Applied voltage");
    model.param().set("Rcurv", "250[um]");
    model.param().descr("Rcurv", "Radius of curvature");
    model.param().set("a", "1/Rcurv/2*1[cm]");
    model.param().descr("a", "Parabola parameter in cm");
    model.param().set("gap", "3.3[mm]");
    model.param().descr("gap", "Gap");

    model.component("comp1").probe().create("var1", "GlobalVariable");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").probe("var1").set("probename", "i0");
    model.component("comp1").probe("var1").set("expr", "edis.I0_0");
    model.component("comp1").probe("var1").set("unit", "mA");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.04, 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", 0.2);
    model.component("comp1").geom("geom1").feature("pc1").set("coord", new String[]{"s", "a*(s)^2*1[cm]+gap"});
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
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"80[um]", "80[um]"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "gap-85[um]"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("edis").prop("Stabilization").set("StreamlineDiffusion", false);
    model.component("comp1").physics("edis").prop("Stabilization").set("IsotropicDiffusion", true);
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
    model.component("comp1").physics("edis").feature("gas1").create("ece1", "Electrode", 1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").selection().set(12, 13);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("V0", "V0");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("eBnd", "SurfaceEmission");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("nBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("item.n0_n", "1E5[1/cm^3]");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("SecondaryElectronEmission", true);
    model.component("comp1").physics("edis").feature("gas1").create("ece2", "Electrode", 1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").selection().set(2, 9);
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").set("pBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").set("item.n0_p", "1E5[1/cm^3]");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature().move("map1", 1);
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 80);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(3, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 80);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(1, 5, 12);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1/1000");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "1/200");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(12, 13);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "\u00b5s");
    model.study("std1").feature("time").set("tlist", "range(0,3,27) 27.2 27.276 27.4 30");
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "1e-15[s]");
    model.sol("sol1").feature("t1").feature("dDef").set("linsolver", "pardiso");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").selection().geom("geom1", 2);
    model.result().dataset("mir1").selection().geom("geom1", 2);
    model.result().dataset("mir1").selection().set(1, 2);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").set("data", "none");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendstitle", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("data", "mir1");
    model.result("pg2").feature("surf1").setIndex("looplevel", 11, 0);
    model.result("pg2").feature("surf1").set("expr", "edis.n_e");
    model.result("pg2").feature("surf1").set("unit", "1/cm^3");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").create("trn1", "Transformation");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("trn1").set("move", new double[]{0.03, 0});
    model.result("pg2").run();
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("data", "dset1");
    model.result("pg2").feature("ann1").setIndex("looplevel", 11, 0);
    model.result("pg2").feature("ann1").set("exprprecision", 3);
    model.result("pg2").feature("ann1").set("showpoint", false);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").feature().duplicate("ann2", "ann1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").setIndex("looplevel", 12, 0);
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("trn1").set("move", new double[]{0.13, 0});
    model.result("pg2").run();
    model.result("pg2").feature("ann2").setIndex("looplevel", 12, 0);
    model.result("pg2").feature("ann2").set("posxexpr", 0.1);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf3", "surf2");
    model.result("pg2").feature().duplicate("ann3", "ann2");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").setIndex("looplevel", 13, 0);
    model.result("pg2").run();
    model.result("pg2").feature("surf3").feature("trn1").set("move", new double[]{0.23, 0});
    model.result("pg2").run();
    model.result("pg2").feature("ann3").setIndex("looplevel", 13, 0);
    model.result("pg2").feature("ann3").set("posxexpr", 0.2);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").set("data", "none");
    model.result("pg3").set("edges", false);
    model.result("pg3").set("showlegendstitle", true);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "mir1");
    model.result("pg3").feature("surf1").setIndex("looplevel", 11, 0);
    model.result("pg3").feature("surf1").set("expr", "edis.normE");
    model.result("pg3").feature("surf1").set("unit", "kV/cm");
    model.result("pg3").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg3").feature("surf1").create("trn1", "Transformation");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("trn1").set("move", new double[]{0.03, 0});
    model.result("pg3").run();
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("data", "dset1");
    model.result("pg3").feature("ann1").setIndex("looplevel", 11, 0);
    model.result("pg3").feature("ann1").set("exprprecision", 3);
    model.result("pg3").feature("ann1").set("showpoint", false);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature().duplicate("ann2", "ann1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").setIndex("looplevel", 12, 0);
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("trn1").set("move", new double[]{0.13, 0});
    model.result("pg3").run();
    model.result("pg3").feature("ann2").setIndex("looplevel", 12, 0);
    model.result("pg3").feature("ann2").set("posxexpr", 0.1);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf3", "surf2");
    model.result("pg3").feature().duplicate("ann3", "ann2");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").setIndex("looplevel", 13, 0);
    model.result("pg3").run();
    model.result("pg3").feature("surf3").feature("trn1").set("move", new double[]{0.23, 0});
    model.result("pg3").run();
    model.result("pg3").feature("ann3").setIndex("looplevel", 13, 0);
    model.result("pg3").feature("ann3").set("posxexpr", 0.2);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();

    model.title("\u7279\u91cc\u5207\u5c14\u8109\u51b2");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5728\u70b9\u5bf9\u677f\u914d\u7f6e\u4e0b\u7531\u8d1f\u7535\u6655\u653e\u7535\u4ea7\u751f\u7684\u7279\u91cc\u5207\u5c14\u8109\u51b2\uff0c\u5f97\u5230\u4e86\u7535\u5b50\u5bc6\u5ea6\u548c\u7535\u573a\uff0c\u4ee5\u53ca\u653e\u7535\u7535\u6d41\u3002\n\n\u653e\u7535\u7535\u6d41\u6e05\u6670\u5730\u663e\u793a\u51fa\u8109\u51b2\u7279\u6027\uff0c\u5176\u4e2d\u6bcf\u4e2a\u7535\u6d41\u8109\u51b2\u7684\u4e0a\u5347\u65f6\u95f4\u7ea6\u4e3a 40 ns\uff0c\u8870\u51cf\u65f6\u95f4\u7ea6\u4e3a 150 ns\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("trichel_pulses.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
