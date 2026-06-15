/*
 * dynamic_crack_branching.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:13 by COMSOL 6.3.0.290. */
public class dynamic_crack_branching {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Damage");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("pfs", "PhaseFieldSolids", "geom1");

    model.component("comp1").multiphysics().create("pfdmg1", "PhaseFieldDamage", 2);
    model.component("comp1").multiphysics("pfdmg1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pfdmg1").set("PhaseFieldSolids_physics", "pfs");
    model.component("comp1").multiphysics("pfdmg1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/pfs", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/pfdmg1", true);

    model.param().set("E0", "32[GPa]");
    model.param().descr("E0", "Young's modulus");
    model.param().set("nu0", "0.2");
    model.param().descr("nu0", "Poisson's ratio");
    model.param().set("rho0", "2450[kg/m^3]");
    model.param().descr("rho0", "Density");
    model.param().set("vR", "2125[m/s]");
    model.param().descr("vR", "Rayleigh wave speed");
    model.param().set("Gc0", "3[J/m^2]");
    model.param().descr("Gc0", "Critical energy release rate");
    model.param().set("lint", "0.5[mm]");
    model.param().descr("lint", "Internal length scale");
    model.param().set("epsc", "sqrt(3*Gc0/(8*lint*E0))");
    model.param().descr("epsc", "Critical strain, AT1");
    model.param().set("load", "1[MPa]");
    model.param().descr("load", "Applied load");
    model.param().set("height", "40[mm]");
    model.param().descr("height", "Sample height");
    model.param().set("width", "100[mm]");
    model.param().descr("width", "Sample width");
    model.param().set("d0", "1[m]");
    model.param().descr("d0", "Sample thickness");
    model.param().set("he", "lint/4");
    model.param().descr("he", "Element size");
    model.param().set("dtmax", "5e-8[s]");
    model.param().descr("dtmax", "Time step");
    model.param().set("eta", "1e-7");
    model.param().descr("eta", "Residual stiffness factor");

    model.func().create("step1", "Step");
    model.func("step1").set("location", "0[s]");
    model.func("step1").set("smooth", "dtmax");
    model.func("step1").set("locationdef", "beginning");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"width", "height/2"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "width/2", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E0"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});

    model.component("comp1").physics("solid").prop("d").set("d", "d0");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "IncludeInertia");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(5);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3, 6);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "load*step1(t)", "0"});
    model.component("comp1").physics("pfs").feature("pfsm1").set("potentialType", "userDefined");
    model.component("comp1").physics("pfs").feature("pfsm1").set("Q_user", "3*phi/8");
    model.component("comp1").physics("pfs").feature("pfsm1").set("lint", "lint");
    model.component("comp1").physics("pfs").feature("pfsm1")
         .set("D_input", new String[]{"3/4", "0", "0", "0", "3/4", "0", "0", "0", "3/4"});
    model.component("comp1").physics("pfs").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("pfs").feature("sym1").selection().set(5);
    model.component("comp1").physics("pfs").create("ppf1", "PrescribedPhaseField", 1);
    model.component("comp1").physics("pfs").feature("ppf1").selection().set(2);
    model.component("comp1").physics("pfs").feature("ppf1").set("phi0", 1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Wc0", "0.5*E0*epsc^2");
    model.component("comp1").variable("var1").descr("Wc0", "Critical strain energy density, AT1");
    model.component("comp1").variable("var1").set("Wfrac", "Gc0*pfs.gcl");
    model.component("comp1").variable("var1").descr("Wfrac", "Fracture energy density");
    model.component("comp1").variable("var1").set("Wfrac_tot", "2*intop1(Wfrac*d0)");
    model.component("comp1").variable("var1").descr("Wfrac_tot", "Total fracture energy");
    model.component("comp1").variable("var1").set("Ws_tot", "2*solid.Ws_tot");
    model.component("comp1").variable("var1").descr("Ws_tot", "Total elastic strain energy");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").set("intorder", 2);
    model.component("comp1").cpl("intop1").set("frame", "material");

    model.component("comp1").multiphysics("pfdmg1").set("ParentMaterialModel", "lemm1");
    model.component("comp1").multiphysics("pfdmg1").set("phCrackDrivingForce", "userDefined");
    model.component("comp1").multiphysics("pfdmg1").set("Dd", "max(pfdmg1.Ws0,Wc0)*pfdmg1.lint/Gc0");
    model.component("comp1").multiphysics("pfdmg1").set("strainEnergySplit", "volDev");
    model.component("comp1").multiphysics("pfdmg1").set("maxDmg", "1-eta");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "he");
    model.component("comp1").mesh("mesh1").feature("map1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmax", "10*he");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "\u00b5s");
    model.study("std1").feature("time").set("tlist", "range(0,1,75)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("timemethod", "genalpha");
    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("endtimeinterpolation", false);
    model.sol("sol1").feature("t1").set("timestepgenalpha", "dtmax");
    model.sol("sol1").feature("t1").set("rhoinf", 0.5);
    model.sol("sol1").feature("t1").feature("se1").set("segterm", "tol");

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u76f8\u573a (pfs)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "phi");
    model.result("pg2").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("mir1").setIndex("genpoints", 0, 1, 1);
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.misesdGp");
    model.result("pg1").feature("surf1").set("descr", "von Mises \u5e94\u529b\uff0c\u635f\u4f24");
    model.result("pg1").feature("surf1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 10);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("filt1").set("expr", "solid.dmg<0.95");
    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("expr", new String[]{"solid.fax", "solid.fay"});
    model.result("pg1").feature("arwl1")
         .set("descr", "\u5355\u4f4d\u53d8\u5f62\u9762\u79ef\u7684\u529b \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg1").feature("arwl1").set("inheritplot", "surf1");
    model.result("pg1").feature("arwl1").set("inheritarrowscale", false);
    model.result("pg1").feature("arwl1").set("inheritcolor", false);
    model.result("pg1").feature("arwl1").set("inheritrange", false);
    model.result("pg1").feature("arwl1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").set("inheritplot", "surf1");
    model.result("pg1").feature("line1").set("inheritcolor", false);
    model.result("pg1").feature("line1").set("inheritrange", false);
    model.result("pg1").feature("line1").set("inheritheightscale", false);
    model.result("pg1").feature("line1").set("inherittubescale", false);
    model.result("pg1").feature("line1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection().set(1, 3, 6, 7);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature().copy("filt1", "pg1/surf1/filt1");
    model.result("pg1").run();

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "Ws_tot", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "Wfrac_tot", 1);
    model.result("pg3").feature("glob1").set("linemarker", "cycle");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("legendpos", "upperleft");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("max1", "MaxSurface");
    model.result().evaluationGroup("eg1").feature("max1").selection().set(2);
    model.result().evaluationGroup("eg1").feature("max1").setIndex("expr", "if(phi*phit>1e3,phi*phit,-1)", 0);
    model.result().evaluationGroup("eg1").feature("max1").setIndex("descr", "Crack front indicator", 0);
    model.result().evaluationGroup("eg1").feature("max1").set("points", "node");
    model.result().evaluationGroup("eg1").feature("max1").set("includepos", true);
    model.result().evaluationGroup("eg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").setIndex("looplevel", "last", 0);
    model.result("pg2").create("tblp1", "TablePoint");
    model.result("pg2").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg2").feature("tblp1").set("colx", 3);
    model.result("pg2").feature("tblp1").set("coly", 4);
    model.result("pg2").feature("tblp1").set("coldata", 1);
    model.result("pg2").run();

//    Started method call computeCrackTrajectory

    model.result().evaluationGroup("eg1").run();
    model.result().table().create("tblcrk", "Table");
    model.result().table("tblcrk")
         .setTableData(new double[][]{{0, 50, 0, 0, 0}, {1, 50, 0, 0, 0}, {2, 50, 0, 0, 0}, {3, 50, 0, 0, 0}, {4, 50, 0, 0, 0}, {5, 50, 0, 0, 0}, {6, 50.12499999999999, 0, 0.1249999999999929, 0.1249999999999929}, {7, 50.12499999999999, 0, 0.1249999999999929, 0}, {8, 50.12499999999999, 0, 0.1249999999999929, 0}, {9, 50.25, 0, 0.25, 0.1250000000000071}, {10, 50.25, 0, 0.25, 0}, {11, 50.25, 0, 0.25, 0}, {12, 50.5, 0, 0.5, 0.25}, {13, 50.87499999999999, 0, 0.8749999999999929, 0.3749999999999929}, {14, 51.5, 0, 1.5, 0.6250000000000071}, {15, 52.125, 0, 2.125, 0.625}, {16, 52.875, 0, 2.875, 0.75}, {17, 53.625, 0, 3.625, 0.75}, {18, 54.375, 0, 4.375, 0.75}, {19, 55, 0, 5, 0.625}, {20, 55.625, 0, 5.625, 0.625}, {21, 56.12500000000001, 0, 6.125000000000007, 0.5000000000000071}, {22, 56.75, 0, 6.75, 0.6249999999999929}, {23, 57.25, 0, 7.25, 0.5}, {24, 57.875, 0, 7.875, 0.625}, {25, 58.625, 0, 8.625, 0.75}, {26, 59.375, 0, 9.375, 0.75}, {27, 60.5, 0, 10.5, 1.125}, {28, 61.5, 0, 11.5, 1}, {29, 62.65141560817565, 0.026415608175648357, 12.651718579821383, 1.1517185798213834}, {30, 63.75, 0, 13.750620509165113, 1.0989019293437305}, {31, 64.5, 0.625, 14.726901718653444, 0.9762812094883317}, {32, 65.375, 0.75, 15.610785195136629, 0.8838834764831844}, {33, 66.24999999999997, 1.0000000000000002, 16.520798931296667, 0.9100137361600376}, {34, 67.12499999999999, 1.25, 17.430812667456745, 0.9100137361600784}, {35, 68.00000000000001, 1.7500000000000002, 18.43859488599409, 1.0077822185373435}, {36, 68.875, 2.2500000000000004, 19.446377104531397, 1.0077822185373064}, {37, 69.62499999999999, 2.875, 20.422658314019717, 0.9762812094883206}, {38, 70.5, 3.5000000000000004, 21.497948972400057, 1.07529065838034}, {39, 71.25, 3.999999999999999, 22.39933679126605, 0.9013878188659966}, {40, 71.99999999999999, 4.499999999999999, 23.300724610132036, 0.9013878188659855}, {41, 72.62499999999999, 4.874999999999999, 24.0295935969877, 0.7288689868556626}, {42, 73.25000000000001, 5.249999999999998, 24.758462583843386, 0.7288689868556865}, {43, 73.875, 5.624999999999999, 25.487331570699038, 0.7288689868556508}, {44, 74.49999999999999, 5.874999999999999, 26.160477171590838, 0.6731456008917998}, {45, 75.12499999999999, 6.125, 26.833622772482652, 0.6731456008918133}, {46, 75.87500000000001, 6.5, 27.6721482640451, 0.8385254915624466}, {47, 76.75, 6.875000000000001, 28.624119902278075, 0.9519716382329758}, {48, 77.50000000000001, 7.124999999999998, 29.41468931732018, 0.7905694150421074}, {49, 78.375, 7.375000000000001, 30.324703053480235, 0.9100137361600519}, {50, 79.37500000000001, 7.624999999999998, 31.355479459884663, 1.0307764064044282}, {51, 80.25, 7.999999999999998, 32.30745109811764, 0.9519716382329755}, {52, 81.25000000000003, 8.375000000000002, 33.37545156628236, 1.0680004681647193}, {53, 82.24999999999999, 8.624999999999998, 34.40622797268673, 1.030776406404373}, {54, 83.25, 8.874999999999996, 35.43700437909116, 1.0307764064044285}, {55, 84.12500000000003, 9.249999999999998, 36.388976017324175, 0.9519716382330153}, {56, 85.125, 9.499999999999998, 37.41975242372856, 1.0307764064043876}, {57, 85.99999999999999, 10, 38.42753464226587, 1.0077822185373073}, {58, 87.00000000000001, 10.500000000000002, 39.54556863101579, 1.118033988749921}, {59, 88, 10.999999999999998, 40.66360261976567, 1.1180339887498805}, {60, 88.875, 11.625, 41.738893278146, 1.0752906583803294}, {61, 89.62499999999999, 12.125, 42.640281097011986, 0.9013878188659855}, {62, 90.375, 12.625, 43.54166891587799, 0.9013878188660092}, {63, 91.12499999999999, 13.125000000000004, 44.443056734743976, 0.9013878188659875}, {64, 91.74999999999997, 13.625000000000004, 45.24344726442307, 0.800390529679095}, {65, 92.37499999999999, 14.124999999999998, 46.04383779410218, 0.8003905296791138}, {66, 92.875, 14.625, 46.75094457528874, 0.7071067811865588}, {67, 93.5, 15.125, 47.55133510496785, 0.8003905296791061}, {68, 94.12499999999999, 15.624999999999996, 48.35172563464694, 0.8003905296790927}, {69, 94.74999999999999, 16.000000000000004, 49.080594621502605, 0.7288689868556663}, {70, 95.25000000000001, 16.249999999999996, 49.63961161587758, 0.5590169943749697}, {71, 95.74999999999999, 16.5, 50.1986286102525, 0.5590169943749236}, {72, 96.375, 16.75, 50.87177421114433, 0.6731456008918262}, {73, 96.875, 16.875, 51.387162414346534, 0.5153882032022076}, {74, 97.50000000000001, 17.000000000000004, 52.02453985354565, 0.6373774391991127}, {75, 98.125, 17.000000000000007, 52.64953985354563, 0.6249999999999858}});
    model.result().table("tblcrk").label("Crack Trajectory");

//    Finished method call computeCrackTrajectory

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg4").feature("tblp1").set("linemarker", "cycle");
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature("tblp1").set("autoplotlabel", true);
    model.result("pg4").feature("tblp1").set("autoheaders", false);
    model.result("pg4").feature().duplicate("tblp2", "tblp1");
    model.result("pg4").run();
    model.result("pg4").feature("tblp2").set("plotcolumns", new int[]{5});
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "vR", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "mm/us", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "0.6*vR", 1);
    model.result("pg4").feature("glob1").setIndex("unit", "mm/us", 1);
    model.result("pg4").feature("glob1").set("linecolor", "fromtheme");
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").create("tlan1", "TableAnnotation");
    model.result("pg4").feature("tlan1").set("source", "localtable");
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 5, 0, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 1.5, 0, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "$0.6 v_R$", 0, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 5, 1, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2.3, 1, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "$v_R$", 1, 2);
    model.result("pg4").feature("tlan1").set("latexmarkup", true);
    model.result("pg4").feature("tlan1").set("showpoint", false);
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg4").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg4").setIndex("plotonsecyaxis", true, 3, 1);
    model.result("pg4").set("legendlayout", "outside");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u52a8\u6001\u88c2\u7eb9\u5206\u53c9\u7684\u76f8\u573a\u5efa\u6a21");

    model
         .description("\u672c\u4f8b\u4f7f\u7528 AT1 \u76f8\u573a\u635f\u4f24\u6a21\u578b\u5206\u6790\u8106\u6027\u6750\u6599\u52a8\u6001\u65ad\u88c2\u7684\u57fa\u51c6\u95ee\u9898\uff0c\u5176\u4e2d\u5bf9\u4e00\u4e2a\u9884\u7f6e\u88c2\u7eb9\u7684\u5e73\u9762\u62c9\u4f38\u8bd5\u6837\u65bd\u52a0\u77ac\u65f6\u62c9\u4f38\u8f7d\u8377\u3002\u6700\u521d\uff0c\u88c2\u7eb9\u5782\u76f4\u4e8e\u52a0\u8f7d\u65b9\u5411\u4f20\u64ad\uff0c\u968f\u540e\u5f00\u59cb\u5bf9\u79f0\u5206\u53c9\uff0c\u76f4\u81f3\u53d1\u751f\u707e\u96be\u6027\u7834\u574f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.result("pg2").feature("tblp1").set("tablechanged", true);
    model.result("pg2").feature("tblp1").set("showparam", false);

    model.label("dynamic_crack_branching.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
