/*
 * electrostrictive_disc.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:54 by COMSOL 6.3.0.290. */
public class electrostrictive_disc {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnfe1", "ChargeConservationFerroelectric");
    model.component("comp1").physics("es").feature("ccnfe1").selection().all();

    model.component("comp1").multiphysics().create("efe1", "ElectrostrictiveEffect", 2);
    model.component("comp1").multiphysics("efe1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("efe1").set("Electrostatics_physics", "es");
    model.component("comp1").multiphysics("efe1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/efe1", true);

    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4\u53c2\u6570");
    model.param().set("H0", "2[mm]");
    model.param().descr("H0", "\u5706\u76d8\u539a\u5ea6");
    model.param().set("R0", "5[mm]");
    model.param().descr("R0", "\u5706\u76d8\u534a\u5f84");
    model.param().set("Q11", "0.0133[m^4/C^2]");
    model.param().descr("Q11", "\u7535\u81f4\u4f38\u7f29\u8026\u5408\u7cfb\u6570");
    model.param().set("Q12", "-0.00606[m^4/C^2]");
    model.param().descr("Q12", "\u7535\u81f4\u4f38\u7f29\u8026\u5408\u7cfb\u6570");
    model.param().set("E1", "105[GPa]");
    model.param().descr("E1", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nu1", "0.4");
    model.param().descr("nu1", "\u6cca\u677e\u6bd4");
    model.param().set("rho1", "7900[kg/m^3]");
    model.param().descr("rho1", "\u5bc6\u5ea6");
    model.param().set("Ps", "0.2589[C/m^2]");
    model.param().descr("Ps", "\u9971\u548c\u6781\u5316");
    model.param().set("a", "0.86207[MV/m]");
    model.param().descr("a", "\u7574\u58c1\u5bc6\u5ea6");
    model.param().set("Vmax", "2[MV/m]*H0");
    model.param().descr("Vmax", "\u6700\u5927\u7535\u538b");
    model.param().set("F0", "-80[MPa]");
    model.param().descr("F0", "F0");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("V0", "Vmax*sin(2*pi*t[1/s])");
    model.component("comp1").variable("var1").descr("V0", "\u5e94\u7528\u7535\u538b");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R0", "H0/2"});
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"3*R0", "5*H0/2"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").selection().set(1);
    model.component("comp1").physics("solid").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("solid").feature("symp1").selection().set(2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(4);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "F0"});
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(4);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().set(2);
    model.component("comp1").physics("es").feature("pot1").set("V0", "V0/2");
    model.component("comp1").physics("es").feature("ccnfe1").selection().set(1);
    model.component("comp1").physics("es").feature("ccnfe1").set("PolarizationModel", "HyperbolicTangent");
    model.component("comp1").physics("es").feature("ccnfe1").set("alphaJAe_mat", "userdef");
    model.component("comp1").physics("es").feature("ccnfe1").set("alphaJAe", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});

    model.component("comp1").multiphysics("efe1").set("CouplingType", "FullyCoupled");
    model.component("comp1").multiphysics("efe1").set("Q11", "Q11");
    model.component("comp1").multiphysics("efe1").set("Q12", "Q12");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("PMT-PT-BT");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho1"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("Ferroelectric", "Ferroelectric", "Ferroelectric");
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("Psat", new String[]{"Ps"});
    model.component("comp1").material("mat1").propertyGroup("Ferroelectric").set("aJAe", new String[]{"a"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 16);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.01,1)", 0);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "t", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "s", 0);
    model.study("std1").feature("param").setIndex("pname", "t", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "s", 0);
    model.study("std1").feature("param").setIndex("pname", "F0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 -40 -80", 0);
    model.study("std1").feature("param").setIndex("punit", "MPa", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset2solidrev", "Revolve2D");
    model.result().dataset("dset2solidrev").set("data", "dset2");
    model.result().dataset("dset2solidrev").set("revangle", 225);
    model.result().dataset("dset2solidrev").set("startangle", -90);
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2solidrev");
    model.result("pg2").setIndex("looplevel", 101, 0);
    model.result("pg2").setIndex("looplevel", 3, 1);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u52bf (es)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"es.Er", "es.Ez"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "V");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset2");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u52bf\uff0c\u56de\u8f6c\u51e0\u4f55 (es)");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 101, 0);
    model.result("pg4").setIndex("looplevel", 3, 1);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "V");
    model.result("pg4").feature("vol1").set("colortable", "Dipole");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7535\u573a (es)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 101, 0);
    model.result("pg5").setIndex("looplevel", 3, 1);
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "es.normE");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("str1", "Streamline");
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("solutionparams", "parent");
    model.result("pg5").feature("str1").set("expr", new String[]{"es.Er", "es.Ez"});
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.02);
    model.result("pg5").feature("str1").set("maxlen", 0.4);
    model.result("pg5").feature("str1").set("maxsteps", 5000);
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("inheritcolor", false);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("data", "parent");
    model.result("pg5").feature("str1").selection().geom("geom1", 1);
    model.result("pg5").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg5").feature("str1").set("inheritplot", "surf1");
    model.result("pg5").feature("str1").feature().create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg5").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("str1").feature().create("filt1", "Filter");
    model.result("pg5").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().dataset("dset1").set("frametype", "material");
    model.result().dataset("dset2").set("frametype", "material");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").setIndex("looplevel", 26, 0);
    model.result("pg2").label("\u4f4d\u79fb, 3D (solid)");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.disp");
    model.result("pg2").feature("surf1").set("unit", "\u00b5m");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 26, 0);
    model.result("pg3").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u81f4\u4f38\u7f29\u5e94\u53d8");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u8f74\u5e94\u53d8 vs. \u8f74\u5411\u7535\u573a");
    model.result("pg6").set("legendpos", "uppermiddle");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(1);
    model.result("pg6").feature("ptgr1").set("expr", "efe1.emZZ");
    model.result("pg6").feature("ptgr1").set("unit", "ppm");
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "es.EZ");
    model.result("pg6").feature("ptgr1").set("xdataunit", "MV/m");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("autopoint", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6781\u5316");
    model.result("pg7").set("title", "\u8f74\u5411\u6781\u5316 vs. \u8f74\u4e0a\u7535\u573a");
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").set("expr", "es.PZ");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevelinput", "first", 1);
    model.result("pg8").label("\u6b63\u5207\u538b\u7535\u8026\u5408\u7cfb\u6570");
    model.result("pg8")
         .set("title", "\u6b63\u5207\u538b\u7535\u8026\u5408\u7cfb\u6570 vs. \u8f74\u5411\u7535\u573a");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u8026\u5408\u7cfb\u6570");
    model.result("pg8").set("manualgrid", true);
    model.result("pg8").set("xspacing", 0.5);
    model.result("pg8").set("yspacing", "1e-10");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").set("expr", "efe1.dET33");
    model.result("pg8").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg8").feature("ptgr1").setIndex("legends", "dET33", 0);
    model.result("pg8").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr2").set("expr", "efe1.dET31");
    model.result("pg8").feature("ptgr2").setIndex("legends", "dET31", 0);
    model.result("pg8").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr3").set("expr", "efe1.dET15");
    model.result("pg8").feature("ptgr3").setIndex("legends", "dET15", 0);
    model.result("pg8").run();

    model.title("\u7535\u81f4\u4f38\u7f29\u76d8");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u94c1\u7535\u5f39\u6027\u201d\u63a5\u53e3\u6a21\u62df\u5f1b\u8c6b\u94c1\u7535\u4f53\u7b49\u6750\u6599\u4e2d\u7684\u975e\u7ebf\u6027\u6781\u5316\u548c\u7535\u81f4\u4f38\u7f29\u6548\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("electrostrictive_disc.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
