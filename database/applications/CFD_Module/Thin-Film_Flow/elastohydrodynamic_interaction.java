/*
 * elastohydrodynamic_interaction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class elastohydrodynamic_interaction {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Thin-Film_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("tff", "ThinFilmFlowShell", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tff", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.param().set("a", "0.02[m]");
    model.param().descr("a", "\u7403\u9762\u534a\u5f84");
    model.param().set("extent", "a");
    model.param().descr("extent", "\u6da6\u6ed1\u533a\u57df\u8303\u56f4");
    model.param().set("Force", "1.5[N]");
    model.param().descr("Force", "\u4f5c\u7528\u529b");
    model.param().set("b0", "a/10");
    model.param().descr("b0", "\u521d\u59cb\u6cb9\u819c\u9ad8\u5ea6");
    model.param().set("visc_mat2", "0.8[Pa*s]");
    model.param().descr("visc_mat2", "\u6da6\u6ed1\u6cb9\u9ecf\u5ea6");
    model.param().set("density_mat2", "860[kg/m^3]");
    model.param().descr("density_mat2", "\u6da6\u6ed1\u6cb9\u5bc6\u5ea6");
    model.param().set("timescale", "6*pi*visc_mat2*a^2/Force");
    model.param().descr("timescale", "\u65f6\u95f4\u5c3a\u5ea6");
    model.param().set("nu_steel", "0.28");
    model.param().descr("nu_steel", "\u6cca\u677e\u6bd4");
    model.param().set("E_steel", "205e9[Pa]");
    model.param().descr("E_steel", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("dens_steel", "7850[kg/m^3]");
    model.param().descr("dens_steel", "\u5bc6\u5ea6");
    model.param().set("E_eqv", "E_steel/(1-nu_steel^2)");
    model.param().descr("E_eqv", "\u7b49\u6548\u6768\u6c0f\u6a21\u91cf");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"3*a", "3*a", "6*a"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-6*a"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("specify", "endsr");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("point1", new String[]{"extent", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("point2", new String[]{"0", "extent"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("r", "extent");

    model.component("comp1").view("view1").camera().set("orthoscale", 0.20869703590869904);
    model.component("comp1").view("view1").camera().set("zoomanglefull", 15.403728485107422);
    model.component("comp1").view("view1").camera()
         .set("position", new double[]{-0.17782773077487946, -0.2447553277015686, 0.6304107904434204});
    model.component("comp1").view("view1").camera()
         .set("target", new double[]{0.030000001192092896, 0.030000001192092896, -0.06000000238418579});
    model.component("comp1").view("view1").camera()
         .set("up", new double[]{0.537805438041687, 0.7151340246200562, 0.44648465514183044});
    model.component("comp1").view("view1").camera()
         .set("rotationpoint", new double[]{0.029999999329447746, 0.029999999329447746, -0.05999999865889549});
    model.component("comp1").view("view1").camera()
         .set("viewoffset", new double[]{-0.001519283396191895, -0.02253088355064392});
    model.component("comp1").view("view1").set("locked", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6da6\u6ed1\u6cb9");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(4);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("sel1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("forcetot", "4*intop1(pfilm)");
    model.component("comp1").variable("var1").descr("forcetot", "\u6da6\u6ed1\u6cb9\u7684\u51c0\u529b");
    model.component("comp1").variable("var1").set("r", "sqrt(x^2+y^2)");
    model.component("comp1").variable("var1").descr("r", "\u5f84\u5411\u8ddd\u79bb");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E_eqv"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu_steel"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"dens_steel"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"visc_mat2"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"density_mat2"});

    model.component("comp1").physics("tff").selection().named("sel1");
    model.component("comp1").physics("tff").feature("ffp1").set("hw1", "b+r^2/(2*a)");
    model.component("comp1").physics("tff").feature("ffp1").set("uw_src", "root.comp1.u");
    model.component("comp1").physics("tff").create("sym1", "SymmetryFluid", 1);
    model.component("comp1").physics("tff").feature("sym1").selection().set(4, 5);
    model.component("comp1").physics("tff").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("tff").feature("ge1").setIndex("name", "b", 0, 0);
    model.component("comp1").physics("tff").feature("ge1").setIndex("equation", "Force-forcetot", 0, 0);
    model.component("comp1").physics("tff").feature("ge1").setIndex("initialValueU", "b0", 0, 0);
    model.component("comp1").physics("tff").feature("ge1").setIndex("initialValueUt", "b0/timescale", 0, 0);
    model.component("comp1").physics("tff").feature("ge1")
         .setIndex("description", "\u6cb9\u819c\u9ad8\u5ea6\u7684\u53d8\u5316", 0, 0);
    model.component("comp1").physics("tff").feature("ge1").set("DependentVariableQuantity", "length");
    model.component("comp1").physics("tff").feature("ge1").set("SourceTermQuantity", "force");
    model.component("comp1").physics("tff").create("ge2", "GlobalEquations", -1);
    model.component("comp1").physics("tff").feature("ge2").setIndex("name", "k", 0, 0);
    model.component("comp1").physics("tff").feature("ge2")
         .setIndex("equation", "timescale*kt+k/(1-2*a*k/(extent^2+2*a*k)-extent^2*(2*a*k)/(extent^2+2*a*k)^2)", 0, 0);
    model.component("comp1").physics("tff").feature("ge2").setIndex("initialValueU", "b0", 0, 0);
    model.component("comp1").physics("tff").feature("ge2").setIndex("initialValueUt", "b0/timescale", 0, 0);
    model.component("comp1").physics("tff").feature("ge2")
         .setIndex("description", "\u6cb9\u819c\u9ad8\u5ea6\u7684\u89e3\u6790\u53d8\u5316", 0, 0);
    model.component("comp1").physics("tff").feature("ge2").set("DependentVariableQuantity", "length");
    model.component("comp1").physics("tff").feature("ge2").set("SourceTermQuantity", "length");

    model.component("comp1").variable("var1")
         .set("analytical_p", "-6*visc_mat2*kt*(2*a^3/(2*a*k+r^2)^2-2*a^3/(2*a*k+extent^2)^2)");
    model.component("comp1").variable("var1").descr("analytical_p", "\u89e3\u6790\u538b\u529b");

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("sel1");
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "ForceDefArea");
    model.component("comp1").physics("solid").feature("bndl1").set("forceDeformedArea_src", "root.comp1.tff.fwallx");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(3);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 2);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1.92e-4");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.15);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,2.0e-4,0.006)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "0.0001");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").set("atolglobal", "1e-5");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6d41\u4f53\u538b\u529b (tff)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 31, 0);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"length", "\u957f\u5ea6", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().named("sel1");
    model.result().dataset().create("sec1", "Sector2D");
    model.result().dataset("sec1").set("sectors", 4);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6d41\u4f53\u538b\u529b\uff0c\u4e8c\u7ef4");
    model.result("pg3").set("data", "sec1");
    model.result("pg3").set("legendpos", "bottom");
    model.result("pg3").set("axisactive", true);
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("number", 8);
    model.result("pg3").feature("con1").set("contourtype", "filled");
    model.result("pg3").feature("con1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").feature("con1").set("colortabletrans", "reverse");
    model.result("pg3").feature("con1").create("hght1", "Height");
    model.result("pg3").run();
    model.result("pg3").feature("con1").feature("hght1").set("heightdata", "expr");
    model.result("pg3").feature("con1").feature("hght1").set("expr", "tff.h");
    model.result("pg3").feature("con1").feature("hght1").set("unit", "m");
    model.result("pg3").feature("con1").feature("hght1").set("scaleactive", true);
    model.result("pg3").feature("con1").feature("hght1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("vol1").feature("def").active(false);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(2);
    model.result("pg4").feature("ptgr1").set("descractive", true);
    model.result("pg4").feature("ptgr1").set("descr", "\u6700\u5927\u538b\u529b\u8ba1\u7b97\u503c");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("autopoint", false);
    model.result("pg4").feature("ptgr1").set("autodescr", true);
    model.result("pg4").run();
    model.result("pg4").create("ptgr2", "PointGraph");
    model.result("pg4").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr2").set("linewidth", "preference");
    model.result("pg4").feature("ptgr2").selection().set(2);
    model.result("pg4").feature("ptgr2").set("expr", "analytical_p");
    model.result("pg4").feature("ptgr2").set("linestyle", "none");
    model.result("pg4").feature("ptgr2").set("linecolor", "blue");
    model.result("pg4").feature("ptgr2").set("linemarker", "asterisk");
    model.result("pg4").feature("ptgr2").set("markerpos", "interp");
    model.result("pg4").feature("ptgr2").set("markers", 20);
    model.result("pg4").feature("ptgr2").set("legend", true);
    model.result("pg4").feature("ptgr2").set("autopoint", false);
    model.result("pg4").feature("ptgr2").set("autodescr", true);
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "b", 0);
    model.result("pg4").feature("glob1").set("linecolor", "magenta");
    model.result("pg4").run();
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").setIndex("expr", "k", 0);
    model.result("pg4").feature("glob2").set("linestyle", "none");
    model.result("pg4").feature("glob2").set("linecolor", "magenta");
    model.result("pg4").feature("glob2").set("linemarker", "asterisk");
    model.result("pg4").feature("glob2").set("markerpos", "interp");
    model.result("pg4").feature("glob2").set("markers", 20);
    model.result("pg4").run();
    model.result("pg4").label("\u6700\u5927\u538b\u529b\u548c\u6cb9\u819c\u9ad8\u5ea6\u53d8\u5316");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg4").setIndex("plotonsecyaxis", true, 3, 1);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg4").set("yseclabelactive", true);
    model.result("pg4").set("legendpos", "uppermiddle");
    model.result("pg4").run();

    model.title("\u77ac\u6001\u5f39\u6d41\u6da6\u6ed1\u6324\u538b\u6cb9\u819c\u76f8\u4e92\u4f5c\u7528");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u8ba1\u7b97\u4e0e\u901a\u8fc7\u6da6\u6ed1\u6cb9\u819c\u9694\u5f00\u7684\u56fa\u4f53\u7403\u548c\u5f39\u6027\u58c1\u975e\u4fdd\u5f62\u7ed3\u5408\u7684\u6da6\u6ed1\u538b\u819c\u8f74\u627f\u4e2d\u7684\u77ac\u6001\u538b\u529b\u5206\u5e03\u548c\u6cb9\u819c\u9ad8\u5ea6\u3002\u8be5\u6a21\u578b\u6c42\u89e3\u6da6\u6ed1\u6cb9\u819c\u9694\u5f00\u7684\u56fa\u4f53\u7403\u4e0e\u58c1\u4e4b\u95f4\u7684\u6d41\u4f53\u52a8\u529b\u5b66\u76f8\u4e92\u4f5c\u7528\u57fa\u51c6\u60c5\u51b5\uff0c\u5e76\u901a\u8fc7\u6269\u5c55\u5f15\u5165\u4e86\u63a5\u89e6\u58c1\u7684\u5f39\u6027\u53d8\u5f62\u548c\u5e94\u529b\u3002\u5728\u5efa\u7acb\u6a21\u578b\u65f6\uff0c\u56fa\u4f53\u7403\u53d7\u5916\u529b\u4f5c\u7528\u6eda\u5411\u56fa\u4f53\u5e73\u9762\u58c1\u3002\u6da6\u6ed1\u5c42\u88ab\u9760\u8fd1\u7684\u7403\u6324\u538b\uff0c\u5bfc\u81f4\u6da6\u6ed1\u538b\u529b\u5347\u9ad8\u3002\u8ba1\u7b97\u7684\u6700\u5927\u6da6\u6ed1\u538b\u529b\u4e0e\u6cb9\u819c\u9ad8\u5ea6\u968f\u65f6\u95f4\u7684\u53d8\u5316\u60c5\u51b5\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("elastohydrodynamic_interaction.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
