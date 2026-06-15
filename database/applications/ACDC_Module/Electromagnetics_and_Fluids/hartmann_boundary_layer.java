/*
 * hartmann_boundary_layer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class hartmann_boundary_layer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Fluids");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").prop("d").set("d", "1[m]");
    model.component("comp1").physics("mf").create("alf1", "AmperesLawFluid");
    model.component("comp1").physics("mf").feature("alf1").selection().all();
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.component("comp1").multiphysics().create("mhd1", "Magnetohydrodynamics", 2);
    model.component("comp1").multiphysics("mhd1").set("EMForce_physics", "mf");
    model.component("comp1").multiphysics("mhd1").set("FluidFlow_physics", "spf");
    model.component("comp1").multiphysics("mhd1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mhd1", true);

    model.param().set("Ha", "10");
    model.param().descr("Ha", "\u54c8\u7279\u66fc\u6570");
    model.param().set("d", "1[cm]");
    model.param().descr("d", "\u534a\u677f\u95f4\u8ddd");
    model.param().set("dens", "1000[kg/m^3]");
    model.param().descr("dens", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("sigma0", "1e7[S/m]");
    model.param().descr("sigma0", "\u6d41\u4f53\u7535\u5bfc\u7387");
    model.param().set("visc", "1e-3[Pa*s]");
    model.param().descr("visc", "\u6d41\u4f53\u9ecf\u5ea6");
    model.param().set("U0", "0.05[m/s]");
    model.param().descr("U0", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6");
    model.param().set("H0", "Ha/mu0_const/d/sqrt(sigma0/visc)");
    model.param().descr("H0", "\u5916\u52a0\u78c1\u573a");
    model.param().set("B0", "mu0_const*H0");
    model.param().descr("B0", "\u78c1\u901a\u5bc6\u5ea6");
    model.param().set("Re", "dens*U0*2*d/visc");
    model.param().descr("Re", "\u96f7\u8bfa\u6570");
    model.param().set("Exx", "-mu0_const*H0*U0");
    model.param().descr("Exx", "\u611f\u5e94\u7535\u573a");
    model.param().set("J0", "sigma0*Exx");
    model.param().descr("J0", "\u611f\u5e94\u7535\u6d41\u5bc6\u5ea6");

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u901f\u5ea6\u5206\u5e03");
    model.func("an1").set("funcname", "an_U");
    model.func("an1").set("expr", "(cosh(Ha)-cosh(Ha*Y))/(cosh(Ha)-1/Ha*sinh(Ha))");
    model.func("an1").set("args", "Ha, Y");
    model.func("an1").setIndex("plotargs", 20, 0, 1);
    model.func("an1").setIndex("plotargs", 20, 0, 2);
    model.func("an1").setIndex("plotargs", -1, 1, 1);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"80*d", "2*d"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-d"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6d41\u4f53");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"dens"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"visc"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("multipleInlets", false);
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "U0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(4);
    model.component("comp1").physics("mf").prop("BackgroundField").set("SolveFor", "ReducedField");
    model.component("comp1").physics("mf").prop("BackgroundField").set("BackgroundFieldType", "Bb");
    model.component("comp1").physics("mf").prop("BackgroundField").set("Bb", new String[]{"0", "B0", "0"});
    model.component("comp1").physics("mf").create("exvp1", "ExternalMagneticVectorPotential", 1);
    model.component("comp1").physics("mf").feature("exvp1").selection().set(1, 2, 3, 4);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "d");
    model.component("comp1").mesh("mesh1").run("map1");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Ha", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "Ha", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "1 10 100", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Az");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "spf.U");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("udist", 0.015);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("con1").active(false);
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("descr", "\u538b\u529b");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").label("\u7eb5\u5207\u9762");
    model.result().dataset("cln1").setIndex("genpoints", "80*d", 1, 0);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").setIndex("genpoints", "70*d", 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", "-d", 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", "70*d", 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", "d", 1, 1);
    model.result().dataset("cln2").label("\u6a2a\u622a\u9762");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u4e2d\u5fc3\u7ebf\u4e0a\u7684\u5f52\u4e00\u5316\u901f\u5ea6");
    model.result("pg4").set("data", "cln1");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "x/d");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "u/U0");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("data", "cln1");
    model.result("pg4").feature("lngr1").set("expr", "u/U0");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x/d");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendprefix", "Ha = ");
    model.result("pg4").feature("lngr1").set("titletype", "none");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5f52\u4e00\u5316\u901f\u5ea6\u5206\u5e03");
    model.result("pg5").set("data", "cln2");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("xlabel", "y/d");
    model.result("pg5").set("ylabel", "u/U0");
    model.result("pg5").set("legendpos", "lowermiddle");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "u/U0");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "y/d");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendprefix", "Ha = ");
    model.result("pg5").feature("lngr1").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr2").set("linewidth", "preference");
    model.result("pg5").feature("lngr2").set("expr", "an_U(Ha,y/d)");
    model.result("pg5").feature("lngr2").set("xdata", "expr");
    model.result("pg5").feature("lngr2").set("xdataexpr", "y/d");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendprefix", "\u89e3\u6790 Ha = ");
    model.result("pg5").feature("lngr2").set("linestyle", "none");
    model.result("pg5").feature("lngr2").set("linecolor", "black");
    model.result("pg5").feature("lngr2").set("linemarker", "cycle");
    model.result("pg5").feature("lngr2").set("resolution", "norefine");
    model.result("pg5").feature("lngr2").set("smooth", "none");
    model.result("pg5").feature("lngr2").set("titletype", "none");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5f52\u4e00\u5316\u901f\u5ea6\u8bef\u5dee");
    model.result("pg6").set("data", "cln2");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "y/d");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "err(u/U0)");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "abs(u/U0-an_U(Ha, y/d))");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "y/d");
    model.result("pg6").feature("lngr1").set("linewidth", 2);
    model.result("pg6").feature("lngr1").set("linemarker", "asterisk");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendprefix", "Ha = ");
    model.result("pg6").feature("lngr1").set("resolution", "norefine");
    model.result("pg6").feature("lngr1").set("smooth", "none");
    model.result("pg6").feature("lngr1").set("titletype", "none");
    model.result("pg6").run();

    model.title("\u54c8\u7279\u66fc\u8fb9\u754c\u5c42");

    model
         .description("\u8fd9\u4e2a\u4ecb\u7ecd\u6027\u7684\u201c\u78c1\u6d41\u4f53\u52a8\u529b\u5b66\u201d\u6a21\u578b\u6f14\u793a\u5916\u90e8\u78c1\u573a\u5bf9\u5bfc\u7535\u6d41\u4f53\u7684\u5c42\u6d41\u901f\u5ea6\u573a\u5206\u5e03\u7684\u5f71\u54cd\u3002\u4e0e\u78c1\u573a\u65b9\u5411\u5782\u76f4\u7684\u6d41\u4f53\u901f\u5ea6\u901a\u8fc7\u7535\u52a8\u52bf\u5f15\u8d77\u4f53\u7535\u6d41\uff0c\u4ece\u800c\u5728\u4e0e\u6d41\u52a8\u65b9\u5411\u76f8\u53cd\u7684\u65b9\u5411\u4e0a\u5bf9\u6d41\u4f53\u4ea7\u751f\u6d1b\u4f26\u5179\u529b\u3002\u5176\u4e2d\u5c06\u4e24\u4e2a\u65e0\u6ed1\u79fb\u58c1\u4e4b\u95f4\u7684\u6d41\u4f53\u901f\u5ea6\u5206\u5e03\u4e0e\u54c8\u7279\u66fc\u6570\u4e0d\u540c\u503c\u7684\u89e3\u6790\u503c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("hartmann_boundary_layer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
