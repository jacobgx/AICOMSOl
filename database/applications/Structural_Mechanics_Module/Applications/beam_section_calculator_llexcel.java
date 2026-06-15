/*
 * beam_section_calculator_llexcel.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:59 by COMSOL 6.3.0.290. */
public class beam_section_calculator_llexcel {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("bcs", "BeamCrossSection", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/bcs", true);

    model.param().set("Smax", "335[MPa]");
    model.param().descr("Smax", "Maximum allowable stress");
    model.param().set("x0", "0");
    model.param().descr("x0", "Reference point, x-coordinate");
    model.param().set("y0", "0");
    model.param().descr("y0", "Reference point, y-coordinate");
    model.param().set("N", "1");
    model.param().descr("N", "Axial force");
    model.param().set("M1", "1");
    model.param().descr("M1", "Bending moment around 1-axis");
    model.param().set("T2", "1");
    model.param().descr("T2", "Shear force along 2-axis");
    model.param().set("M2", "1");
    model.param().descr("M2", "Bending moment around 2-axis");
    model.param().set("T1", "1");
    model.param().descr("T1", "Shear force along 1-axis");
    model.param().set("Mt", "1");
    model.param().descr("Mt", "Twisting moment");

    model.geom()
         .load(new String[]{"part1"}, "Structural_Mechanics_Module/Beams/Generic/C_beam_generic.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part2"}, "Structural_Mechanics_Module/Beams/Generic/I_beam_generic.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part3"}, "Structural_Mechanics_Module/Beams/Generic/L_beam_generic.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part4"}, "Structural_Mechanics_Module/Beams/Generic/T_beam_generic.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").lengthUnit("in");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d", "4.16[in]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "b", "4.06[in]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "tw", "0.28[in]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "tf", "0.345[in]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "r1", "0.25[in]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "r2", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "slope", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "u", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("bcs").feature("hcs1").set("E_mat", "userdef");
    model.component("comp1").physics("bcs").feature("hcs1").set("nu_mat", "userdef");
    model.component("comp1").physics("bcs").feature("hcs1").set("N", "N");
    model.component("comp1").physics("bcs").feature("hcs1").set("M1", "M1");
    model.component("comp1").physics("bcs").feature("hcs1").set("T2", "T2");
    model.component("comp1").physics("bcs").feature("hcs1").set("M2", "M2");
    model.component("comp1").physics("bcs").feature("hcs1").set("T1", "T1");
    model.component("comp1").physics("bcs").feature("hcs1").set("Mt", "Mt");

    model.sol().create("sol1");
    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").feature("aDef").set("complexfun", false);
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", false);
    model.sol("sol1").feature("s1").feature("aDef").set("matherr", true);
    model.sol("sol1").feature("s1").feature("aDef").set("blocksizeactive", false);
    model.sol("sol1").feature("s1").feature("aDef").set("nullfun", "flnullorth");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("Bending Moment M1 (bcs)");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("defaultPlotID", "bendingMomentM1");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("expr", "bcs.sM1");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("Shear Force T2 (bcs)");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("defaultPlotID", "shearForceT2");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "bcs.restT2");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("showsolutionparams", "on");
    model.result("pg2").feature("arws1").set("solutionparams", "parent");
    model.result("pg2").feature("arws1").set("expr", new String[]{"bcs.tT2x", "bcs.tT2y"});
    model.result("pg2").feature("arws1").set("xnumber", 60);
    model.result("pg2").feature("arws1").set("ynumber", 60);
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").feature("arws1").set("showsolutionparams", "on");
    model.result("pg2").feature("arws1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("Bending Moment M2 (bcs)");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("defaultPlotID", "bendingMomentM2");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "bcs.sM2");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("Shear Force T1 (bcs)");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("defaultPlotID", "shearForceT1");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "bcs.restT1");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("solutionparams", "parent");
    model.result("pg4").feature("arws1").set("expr", new String[]{"bcs.tT1x", "bcs.tT1y"});
    model.result("pg4").feature("arws1").set("xnumber", 60);
    model.result("pg4").feature("arws1").set("ynumber", 60);
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("Torsion (bcs)");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").set("defaultPlotID", "torsion");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "bcs.restMt");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("solutionparams", "parent");
    model.result("pg5").feature("arws1").set("expr", new String[]{"bcs.tMtx", "bcs.tMty"});
    model.result("pg5").feature("arws1").set("xnumber", 60);
    model.result("pg5").feature("arws1").set("ynumber", 60);
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("Equivalent Stress (bcs)");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").set("defaultPlotID", "stress");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "bcs.mises");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1").label("Section Properties (bcs)");
    model.result().evaluationGroup("eg1").set("defaultPlotID", "sectionPropertiesTable");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("Homogeneous Cross Section 1");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.A", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Area", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.I1", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Largest principal moment of inertia", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.ei1", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Shear center location, principal axes system, 1 component", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.mu2", 3);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Maximum shear stress factor, principal axes system, 2 component", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.kappa2", 4);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Shear correction factor, principal axes system, 2 component", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.I2", 5);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Smallest principal moment of inertia", 5);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.ei2", 6);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Shear center location, principal axes system, 2 component", 6);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.mu1", 7);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Maximum shear stress factor, principal axes system, 1 component", 7);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.kappa1", 8);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Shear correction factor, principal axes system, 1 component", 8);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.J", 9);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Torsional constant", 9);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.Wt", 10);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Torsional section modulus", 10);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.alpha", 11);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Angle from first local axis to first principal axis", 11);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.h1", 12);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Section height in first principal direction", 12);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.bcs.hcs1.h2", 13);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Section height in second principal direction", 13);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("expr", new String[]{"bcs.hcs1.CGx", "0"});
    model.result("pg1").feature("pttraj1").setIndex("expr", "bcs.hcs1.CGy", 1);
    model.result("pg1").feature("pttraj1").set("linetype", "none");
    model.result("pg1").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj1").set("arrowexpr", new String[]{"bcs.hcs1.axis1maxx", "0"});
    model.result("pg1").feature("pttraj1").setIndex("arrowexpr", "bcs.hcs1.axis1maxy", 1);
    model.result("pg1").feature("pttraj1").set("pointcolor", "blue");
    model.result("pg1").run();
    model.result("pg1").create("pttraj2", "PointTrajectories");
    model.result("pg1").feature("pttraj2").set("expr", new String[]{"bcs.hcs1.CGx", "0"});
    model.result("pg1").feature("pttraj2").setIndex("expr", "bcs.hcs1.CGy", 1);
    model.result("pg1").feature("pttraj2").set("linetype", "none");
    model.result("pg1").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj2").set("arrowexpr", new String[]{"bcs.hcs1.axis2maxx", "0"});
    model.result("pg1").feature("pttraj2").setIndex("arrowexpr", "bcs.hcs1.axis2maxy", 1);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("pttraj1", "PointTrajectories");
    model.result("pg2").feature("pttraj1").set("expr", new String[]{"bcs.hcs1.ex", "0"});
    model.result("pg2").feature("pttraj1").setIndex("expr", "bcs.hcs1.ey", 1);
    model.result("pg2").feature("pttraj1").set("linetype", "none");
    model.result("pg2").feature("pttraj1").set("pointtype", "point");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("pttraj1", "PointTrajectories");
    model.result("pg3").feature("pttraj1").set("expr", new String[]{"bcs.hcs1.CGx", "0"});
    model.result("pg3").feature("pttraj1").setIndex("expr", "bcs.hcs1.CGy", 1);
    model.result("pg3").feature("pttraj1").set("linetype", "none");
    model.result("pg3").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg3").feature("pttraj1").set("arrowexpr", new String[]{"bcs.hcs1.axis1maxx", "0"});
    model.result("pg3").feature("pttraj1").setIndex("arrowexpr", "bcs.hcs1.axis1maxy", 1);
    model.result("pg3").feature("pttraj1").set("pointcolor", "blue");
    model.result("pg3").run();
    model.result("pg3").create("pttraj2", "PointTrajectories");
    model.result("pg3").feature("pttraj2").set("expr", new String[]{"bcs.hcs1.CGx", "0"});
    model.result("pg3").feature("pttraj2").setIndex("expr", "bcs.hcs1.CGy", 1);
    model.result("pg3").feature("pttraj2").set("linetype", "none");
    model.result("pg3").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg3").feature("pttraj2").set("arrowexpr", new String[]{"bcs.hcs1.axis2maxx", "0"});
    model.result("pg3").feature("pttraj2").setIndex("arrowexpr", "bcs.hcs1.axis2maxy", 1);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("pttraj1", "PointTrajectories");
    model.result("pg4").feature("pttraj1").set("expr", new String[]{"bcs.hcs1.ex", "0"});
    model.result("pg4").feature("pttraj1").setIndex("expr", "bcs.hcs1.ey", 1);
    model.result("pg4").feature("pttraj1").set("linetype", "none");
    model.result("pg4").feature("pttraj1").set("pointtype", "point");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").set("showlegendsunit", true);
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("Warping (bcs)");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").set("defaultPlotID", "warping");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "bcs.uw");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").feature().create("hght1", "Height");
    model.result("pg7").feature("surf1").feature("hght1").set("heightdata", "expr");
    model.result("pg7").feature("surf1").feature("hght1").set("expr", "0.1*bcs.uw*bcs.maxh/bcs.uw_max");
    model.result("pg7").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg7").feature("surf1").feature("hght1").set("isheightaxisshown", false);
    model.result("pg7").label("Warping (bcs)");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").set("showlegendsunit", true);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.A", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in^2", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Area", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.I1", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in^4", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Largest principal moment of inertia", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.I2", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in^4", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Smallest principal moment of inertia", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.J", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in^4", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Torsional constant", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.Wt", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in^3", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Torsional section modulus", 4);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "bcs.hcs1.ei1*(abs(bcs.hcs1.ei1)>=bcs.hcs1.h1/100)", 5);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in", 5);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Shear center location, principal axes system, 1 component", 5);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "bcs.hcs1.ei2*(abs(bcs.hcs1.ei2)>=bcs.hcs1.h2/100)", 6);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in", 6);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Shear center location, principal axes system, 2 component", 6);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.mu2", 7);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", 1, 7);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Maximum shear stress factor, 2 component", 7);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.mu1", 8);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", 1, 8);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Maximum shear stress factor, 1 component", 8);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.kappa1", 9);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", 1, 9);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Shear correction factor, 1 component", 9);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.kappa2", 10);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", 1, 10);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Shear correction factor, 2 component", 10);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.alpha", 11);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "rad", 11);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Angle from x-axis to first principal axis", 11);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.h1", 12);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in", 12);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Section height in local 1 direction", 12);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.h2", 13);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in", 13);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "Section height in local 2 direction", 13);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "(bcs.hcs1.CGx-x0)*(abs((bcs.hcs1.CGx-x0)/(bcs.hcs1.maxbcs(x)-bcs.hcs1.minbcs(x)))>1e-4)", 14);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in", 14);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Center of gravity, x-component", 14);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "(bcs.hcs1.CGy-y0)*(abs((bcs.hcs1.CGy-y0)/(bcs.hcs1.maxbcs(y)-bcs.hcs1.minbcs(y)))>1e-4)", 15);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "in", 15);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Center of gravity, y-component", 15);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "bcs.hcs1.maxbcs(bcs.mises)<=Smax", 16);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", 1, 16);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Smax", 16);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "MPa");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("unit", "MPa");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("unit", "MPa");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("unit", "MPa");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("unit", "MPa");

    model.view("view6").camera().set("zoomanglefull", 11.7);
    model.view("view6").camera().setIndex("position", 14.6, 0);
    model.view("view6").camera().setIndex("position", 10.7, 1);
    model.view("view6").camera().set("position", new double[]{14.6, 10.7, 32.7});
    model.view("view6").camera().setIndex("up", -0.11, 0);
    model.view("view6").camera().setIndex("up", 0.96, 1);
    model.view("view6").camera().set("up", new double[]{-0.11, 0.96, -0.26});
    model.view("view6").camera().setIndex("viewoffset", "-5.48e-2", 0);
    model.view("view6").camera().set("viewoffset", new String[]{"-5.48e-2", "2.72e-2"});

    model.result("pg1").run();

    model.title("\u6881\u622a\u9762\u8ba1\u7b97\u5668\uff08\u4f7f\u7528 LiveLink\u2122 for Excel\u00ae\uff09");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4ece Excel\u00ae \u6587\u4ef6\u8bfb\u53d6\u548c\u5bfc\u5165\u6570\u636e\n\u2022 \u5c06\u6570\u636e\u5bfc\u51fa\u81f3 Excel\u00ae \u6587\u4ef6\n\u2022 \u6d45\u8272\u4e3b\u9898\n\n\u8be5 App \u8ba1\u7b97\u4e00\u4e2a\u6307\u5b9a\u94a2\u6881\u622a\u9762\u7684\u6881\u622a\u9762\u5c5e\u6027\u548c\u771f\u5b9e\u5e94\u529b\u5206\u5e03\uff0c\u5e76\u63d0\u4f9b\u5404\u79cd\u7f8e\u56fd\u548c\u6b27\u6d32\u6807\u51c6\u7684\u6881\u3002\u5176\u4e2d\u4f7f\u7528 LiveLink\u2122 for Excel\u00ae \u5728 Excel\u00ae \u5de5\u4f5c\u8868\u4e2d\u8bfb\u53d6\u548c\u5b58\u50a8\u6881\u6570\u636e\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u548c LiveLink\u2122 for Excel\u00ae\u3002");

    model.label("beam_section_calculator_llexcel.mph");

    model.result("pg1").run();

    model.setExpectedComputationTime("3 seconds");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("filename", "user:///beam_section_evaluation.docx");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature("tp1").set("includeauthor", false);
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1")
         .set("summary", "The current document contains the geometry and mechanical properties for the beam type BeamType.");
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includepath", false);
    model.result().report("rpt1").feature("sec1").feature("root1").set("includename", false);
    model.result().report("rpt1").feature("sec1").feature("root1").set("includeusedproducts", false);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec2").feature("geom1").set("includestats", false);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature().create("img1", "Graphics");
    model.result().report("rpt1").feature("sec2").feature("img1").set("source", "external");
    model.result().report("rpt1").feature("sec2").feature("img1").set("externalfile", "embedded:///ibeam_appimage");
    model.result().report("rpt1").feature("sec2").feature().create("btbl1", "Tbl");
    model.result().report("rpt1").feature("sec2").feature("btbl1").set("ncols", 4);
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("tblhrow1", "TblHRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("tblhrow1").set("hcol1", "Name");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("tblhrow1").set("hcol2", "Value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("tblhrow1").set("hcol3", "Unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("tblhrow1").set("hcol4", "Description");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow1", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow1").set("col1", "d");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow1").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow1").set("col3", "unit");

    return model;
  }

  public static Model run2(Model model) {
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow1").set("col4", "Beam height");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow2", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow2").set("col1", "b");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow2").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow2").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow2").set("col4", "Flange width");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow3", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow3").set("col1", "tw");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow3").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow3").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow3").set("col4", "Web thickness");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow4", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow4").set("col1", "tf");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow4").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow4").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow4").set("col4", "Flange thickness");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow5", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow5").set("col1", "r1");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow5").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow5").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow5").set("col4", "Web fillet radius");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow6", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow6").set("col1", "r2");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow6").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow6").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow6").set("col4", "Web fillet radius");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow7", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow7").set("col1", "slope");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow7").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow7").set("col3", "%");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow7").set("col4", "Flange slope");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow8", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow8").set("col1", "u");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow8").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow8").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow8")
         .set("col4", "Flange thickness evaluation location");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow9", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow9").set("col1", "h0");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow9").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow9").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow9")
         .set("col4", "Vertical leg length");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow10", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow10").set("col1", "b0");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow10").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow10").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow10")
         .set("col4", "Horizontal leg length");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow11", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow11").set("col1", "th");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow11").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow11").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow11").set("col4", "Leg thickness");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow12", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow12").set("col1", "r1");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow12").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow12").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow12")
         .set("col4", "Inner fillet radius");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow13", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow13").set("col1", "r2");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow13").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow13").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow13")
         .set("col4", "Leg fillet radius");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature().create("trow14", "TblRow");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow14").set("col1", "r3");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow14").set("col2", "value");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow14").set("col3", "unit");
    model.result().report("rpt1").feature("sec2").feature("btbl1").feature("trow14")
         .set("col4", "Outer fillet radius");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature().create("btbl1", "Tbl");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("tblhrow1", "TblHRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("tblhrow1").set("hcol1", "Description");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("tblhrow1").set("hcol2", "Value");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("tblhrow1").set("hcol3", "Unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow1", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow1").set("col1", "Area");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow1").set("col2", "A");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow1").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow2", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow2")
         .set("col1", "Largest Principal Moment of Inertia");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow2").set("col2", "I1");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow2").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow3", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow3")
         .set("col1", "Smallest Principal Moment of Inertia");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow3").set("col2", "I2");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow3").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow4", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow4")
         .set("col1", "Torsional Constant");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow4").set("col2", "J");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow4").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow5", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow5")
         .set("col1", "Torsional Section Modulus");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow5").set("col2", "Wt");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow5").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow6", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow6").set("col1", "Shear Center, X1");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow6").set("col2", "ei1");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow6").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow7", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow7").set("col1", "Shear Center, X2");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow7").set("col2", "ei2");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow7").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow8", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow8")
         .set("col1", "Shear Stress Factor, X2");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow8").set("col2", "mu2");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow8").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow9", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow9")
         .set("col1", "Shear Stress Factor, X1");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow9").set("col2", "mu1");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow9").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow10", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow10")
         .set("col1", "Shear Correction Factor, X2");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow10").set("col2", "kappa2");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow10").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow11", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow11")
         .set("col1", "Shear Correction Factor, X1");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow11").set("col2", "kappa1");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow11").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow12", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow12")
         .set("col1", "Angle from x-axis to first principal axis");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow12").set("col2", "alpha");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow12").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow13", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow13")
         .set("col1", "Section height in local 1 direction");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow13").set("col2", "h1");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow13").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow14", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow14")
         .set("col1", "Section height in local 2 direction");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow14").set("col2", "h2");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow14").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow15", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow15")
         .set("col1", "Center of gravity from (x0,y0), x-component");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow15").set("col2", "CGx-x0");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow15").set("col3", "unit");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature().create("trow16", "TblRow");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow16")
         .set("col1", "Center of gravity from (x0,y0), y-component");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow16").set("col2", "CGy-y0");
    model.result().report("rpt1").feature("sec3").feature("btbl1").feature("trow16").set("col3", "unit");
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("txt1", "Text");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("btbl1", "Tbl");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature()
         .create("tblhrow1", "TblHRow");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("tblhrow1")
         .set("hcol1", "Description");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("tblhrow1")
         .set("hcol2", "Value");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("tblhrow1")
         .set("hcol3", "Unit");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature()
         .create("trow1", "TblRow");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow1")
         .set("col1", "Axial force");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow1")
         .set("col2", "Value");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow1").set("col3", "N");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature()
         .create("trow2", "TblRow");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow2")
         .set("col1", "Bending moment around 1-axis");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow2")
         .set("col2", "Value");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow2")
         .set("col3", "N*m");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature()
         .create("trow3", "TblRow");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow3")
         .set("col1", "Shear force along 2-axis");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow3")
         .set("col2", "Value");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow3").set("col3", "N");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature()
         .create("trow4", "TblRow");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow4")
         .set("col1", "Bending moment around 2 -axis");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow4")
         .set("col2", "Value");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow4")
         .set("col3", "N*m");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature()
         .create("trow5", "TblRow");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow5")
         .set("col1", "Shear force along 1-axis");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow5")
         .set("col2", "Value");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow5").set("col3", "N");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature()
         .create("trow6", "TblRow");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow6")
         .set("col1", "Twisting moment");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow6")
         .set("col2", "Value");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature("btbl1").feature("trow6")
         .set("col3", "N*m");
    model.result().report("rpt1").feature("sec4").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec1").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec2").feature("pg1")
         .set("noderef", "pg2");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec3").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec3").feature("pg1")
         .set("noderef", "pg3");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec4").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec4").feature("pg1")
         .set("noderef", "pg4");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec5", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec5").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec5").feature("pg1")
         .set("noderef", "pg5");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("sec6", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec6").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("sec6").feature("pg1")
         .set("noderef", "pg6");
    model.result().report("rpt1").feature("sec4").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("pg1").set("noderef", "pg7");
    model.result().report("rpt1").feature("sec4").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature().create("btbl1", "Tbl");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("btbl1").feature()
         .create("tblhrow1", "TblHRow");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("btbl1").feature("tblhrow1")
         .set("hcol1", "Description");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("btbl1").feature("tblhrow1")
         .set("hcol2", "Value");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("btbl1").feature("tblhrow1")
         .set("hcol3", "Unit");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("btbl1").feature()
         .create("trow1", "TblRow");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("btbl1").feature("trow1")
         .set("col1", "Maximum allowable stress");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("btbl1").feature("trow1")
         .set("col2", "Value");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("btbl1").feature("trow1")
         .set("col3", "N/m^2");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature().create("txt1", "Text");

    app.form("main").formObject("collection1").setIndex("sectioncollapsible", false, 0);
    app.form("main").formObject("collection1").setIndex("sectioncollapsible", false, 1);
    app.form("main").formObject("collection1").setIndex("sectioncollapsible", false, 2);
    app.form("main").formObject("collection1").setIndex("sectioncollapsible", false, 3);

    model.title("\u6881\u622a\u9762\u8ba1\u7b97\u5668\uff08\u4f7f\u7528 LiveLink\u2122 for Excel\u00ae\uff09");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4ece Excel\u00ae \u6587\u4ef6\u8bfb\u53d6\u548c\u5bfc\u5165\u6570\u636e\n\u2022 \u5c06\u6570\u636e\u5bfc\u51fa\u81f3 Excel\u00ae \u6587\u4ef6\n\u2022 \u6d45\u8272\u4e3b\u9898\n\n\u8be5 App \u8ba1\u7b97\u4e00\u4e2a\u6307\u5b9a\u94a2\u6881\u622a\u9762\u7684\u6881\u622a\u9762\u5c5e\u6027\u548c\u771f\u5b9e\u5e94\u529b\u5206\u5e03\uff0c\u5e76\u63d0\u4f9b\u5404\u79cd\u7f8e\u56fd\u548c\u6b27\u6d32\u6807\u51c6\u7684\u6881\u3002\u5176\u4e2d\u4f7f\u7528 LiveLink\u2122 for Excel\u00ae \u5728 Excel\u00ae \u5de5\u4f5c\u8868\u4e2d\u8bfb\u53d6\u548c\u5b58\u50a8\u6881\u6570\u636e\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u548c LiveLink\u2122 for Excel\u00ae\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("beam_section_calculator_llexcel.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
