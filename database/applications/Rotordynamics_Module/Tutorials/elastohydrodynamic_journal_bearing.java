/*
 * elastohydrodynamic_journal_bearing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:47 by COMSOL 6.3.0.290. */
public class elastohydrodynamic_journal_bearing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/hdb", true);

    model.param().set("Omega", "100[rpm]");
    model.param().descr("Omega", "Rotational speed");
    model.param().set("C", "200[um]");
    model.param().descr("C", "Bearing clearance");
    model.param().set("W", "20[kN]");
    model.param().descr("W", "Bearing load");
    model.param().set("ps", "2[bar]");
    model.param().descr("ps", "Supply pressure");
    model.param().set("mu0", "75[mPa*s]");
    model.param().descr("mu0", "Dynamic viscosity at zero pressure");
    model.param().set("xi", "2.5e-8[m^2/N]");
    model.param().descr("xi", "Viscosity-pressure coefficient");
    model.param().set("rho0", "850[kg/m^3]");
    model.param().descr("rho0", "Density at zero pressure");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "rho");
    model.component("comp1").func("an1").set("expr", "rho0*(1+0.6*x/(1.7*x+1e9))");
    model.component("comp1").func("an1").set("fununit", "kg/m^3");
    model.component("comp1").func("an1").setIndex("argunit", "Pa", 0);
    model.component("comp1").func().duplicate("an2", "an1");
    model.component("comp1").func("an2").set("funcname", "mu");
    model.component("comp1").func("an2").set("expr", "mu0*exp(xi*x)");
    model.component("comp1").func("an2").set("fununit", "Pa*s");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "elastohydrodynamic_journal_bearing.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel1").set(14, 15, 16, 17, 18, 19);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection("sel2").add(41);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection("sel3").add(30);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(20, 21, 22, 23, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58);
    model.component("comp1").selection().create("sel5", "Explicit");

    model.component("comp1").view("view1").set("hidestatus", "ignore");

    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(24, 25, 26, 27, 59, 60, 61, 62);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(1);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection("sel6").add(5);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set("groupcontang", true);
    model.component("comp1").selection("sel7").add(1);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").geom(1);
    model.component("comp1").selection("sel8").set(33, 34, 36, 37, 39, 40, 41, 42);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"mu(hdb.p)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho(hdb.p)"});

    model.component("comp1").physics("solid").feature("dcont1").set("pairDisconnect", true);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel3");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("sel5");
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 1);
    model.component("comp1").physics("solid").feature("rig1").selection().named("sel6");
    model.component("comp1").physics("solid").feature("rig1").set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("solid").feature("rig1").setIndex("ConstrainedRotation", true, 0);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("sel4");
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "ps");
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").selection().named("sel1");
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"hdb.fjx", "hdb.fjy", "hdb.fjz"});
    model.component("comp1").physics("solid").create("bndl3", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl3").selection().named("sel2");
    model.component("comp1").physics("solid").feature("bndl3")
         .set("forceReferenceArea", new String[]{"src2dst_ap1(hdb.fbx)", "src2dst_ap1(hdb.fby)", "src2dst_ap1(hdb.fbz)"});
    model.component("comp1").physics("solid").create("bndl4", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl4").selection().named("sel7");
    model.component("comp1").physics("solid").feature("bndl4").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl4").set("force", new String[]{"0", "0", "-W/2"});
    model.component("comp1").physics("hdb").selection().named("sel1");

    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").set("hidestatus", "ignore");
    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").physics("hdb").feature("hjb1").set("C_plain", "C");
    model.component("comp1").physics("hdb").feature("hjb1").set("Xc", new String[]{"0.1125[m]", "0", "0"});
    model.component("comp1").physics("hdb").feature("hjb1").set("uJ_src", "root.comp1.u");
    model.component("comp1").physics("hdb").feature("hjb1").set("JournalVelocity", "RevolutionsPerTime");
    model.component("comp1").physics("hdb").feature("hjb1").set("rpt", "Omega");
    model.component("comp1").physics("hdb").feature("hjb1").feature("mfd1")
         .set("ufd", new String[]{"dst2src_ap1(u)", "dst2src_ap1(v)", "dst2src_ap1(w)"});
    model.component("comp1").physics("hdb").create("sym1", "SymmetryFluid", 1);

    model.component("comp1").view("view1").set("hidestatus", "hide");

    model.component("comp1").physics("hdb").feature("sym1").selection().set(46, 47, 48, 52, 56, 58);
    model.component("comp1").physics("hdb").create("inl1", "Inlet", 1);
    model.component("comp1").physics("hdb").feature("inl1").selection().named("sel8");
    model.component("comp1").physics("hdb").feature("inl1").set("InletCondition", "Pressure");
    model.component("comp1").physics("hdb").feature("inl1").set("pf0", "ps");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(14, 19);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").named("sel1");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").named("sel2");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(16, 17);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(48, 52);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(25, 27, 31);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").feature("conv1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("conv1").selection().set(16, 17);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Omega", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "1/s", 0);
    model.study("std1").feature("stat").setIndex("pname", "Omega", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "1/s", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "10^range(4,-0.5,2)", 0);
    model.study("std1").feature("stat").setIndex("punit", "rpm", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"pressure", "\u538b\u529b", "Pa", "Pa"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "bar", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"planeangle", "\u5e73\u9762\u89d2", "rad", "rad"}, 2);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0", 2, 3);
    model.result().configuration("prfu1").apply();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickx", "0.1125[m]");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("edges", false);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "hdb.p");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("expr", new String[]{"-hdb.nrefx*hdb.p", "v", "w"});
    model.result("pg1").feature("surf1").feature("def1").setIndex("expr", "-hdb.nrefx*hdb.p", 1);
    model.result("pg1").feature("surf1").feature("def1").setIndex("expr", "-hdb.nrefz*hdb.p", 2);
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").set("expr", "1");
    model.result("pg1").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection()
         .set(28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 39, 40, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("legendpos", "rightdouble");
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").set("arrayaxis", "y");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").feature("surf1").set("expr", "solid.disp/C");
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg2").feature("surf1").feature("sel1").selection().set(2);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("mtrl1").set("useplotcolors", true);
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").feature("surf2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("colortable", "RainbowLight");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("sel1").selection().set(1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("edges", false);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").set("arrayaxis", "y");
    model.result("pg3").set("relpadding", 0.1);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").feature("surf1").set("expr", "solid.misesGp");
    model.result("pg3").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg3").feature("surf1").feature("sel1").selection().set(2);
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("sel1").selection().set(1);
    model.result("pg3").run();

    model.component("comp1").physics("hdb").feature().duplicate("hjb2", "hjb1");
    model.component("comp1").physics("hdb").feature("hjb2").set("Specify", "Load");
    model.component("comp1").physics("hdb").feature("hjb2").set("W", new String[]{"0", "0", "-W/2"});
    model.component("comp1").physics("hdb").feature("hjb2").feature("mfd1").set("ufd", new int[]{0, 0, 0});

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"hdb/hjb2"});
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/hdb", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid"});
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "Omega", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "1/s", 0);
    model.study("std2").feature("stat").setIndex("pname", "Omega", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "1/s", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "10^range(4,-0.5,2)", 0);
    model.study("std2").feature("stat").setIndex("punit", "rpm", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(46, 48, 52, 58);
    model.result("pg4").feature("lngr1").set("expr", "hdb.p");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "hdb.Th");
    model.result("pg4").feature("lngr1").set("linewidth", 2);
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("autoplotlabel", true);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("data", "dset2");
    model.result("pg4").feature("lngr2").set("linestyle", "dotted");
    model.result("pg4").feature("lngr2").set("linecolor", "cyclereset");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(46, 48, 52, 58);
    model.result("pg5").feature("lngr1").set("expr", "hdb.h");
    model.result("pg5").feature("lngr1").set("unit", "\u00b5m");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "hdb.Th");
    model.result("pg5").feature("lngr1").set("linewidth", 2);
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").run();
    model.result().dataset().create("surfdset1hjb1hdb", "Surface");
    model.result().dataset("surfdset1hjb1hdb").label("\u8868\u9762 (hjb1)");
    model.result().dataset("surfdset1hjb1hdb").set("data", "dset1");
    model.result().dataset("surfdset1hjb1hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset1hjb1hdb").selection().set(14, 15, 16, 17, 18, 19);
    model.result().dataset("surfdset1hjb1hdb").selection().inherit(false);
    model.result().dataset("surfdset1hjb1hdb").selection().embedded(false);
    model.result().dataset("surfdset1hjb1hdb").set("param", "expr");
    model.result().dataset("surfdset1hjb1hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb1.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb1.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb1.Rj))");
    model.result().dataset("surfdset1hjb1hdb").set("expry", "hdb.hjb1.r1");
    model.result().dataset("surfdset1hjb1hdb").set("defaultPlotIDs", new String[]{"pg2|hdb", "pg3|hdb"});
    model.result().dataset("surfdset1hjb1hdb").label("\u8868\u9762 (hjb1)");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb1)");
    model.result("pg6").set("data", "surfdset1hjb1hdb");
    model.result("pg6").set("edges", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg6").feature("surf1").set("colortabletype", "discrete");
    model.result("pg6").feature("surf1").set("expr", "hdb.p");
    model.result("pg6").feature("surf1").create("hght1", "Height");
    model.result("pg6").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb1)");
    model.result("pg6").run();
    model.result().dataset().create("surfdset2hjb2hdb", "Surface");
    model.result().dataset("surfdset2hjb2hdb").label("\u8868\u9762 (hjb2)");
    model.result().dataset("surfdset2hjb2hdb").set("data", "dset2");
    model.result().dataset("surfdset2hjb2hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset2hjb2hdb").selection().set(14, 15, 16, 17, 18, 19);
    model.result().dataset("surfdset2hjb2hdb").selection().inherit(false);
    model.result().dataset("surfdset2hjb2hdb").selection().embedded(false);
    model.result().dataset("surfdset2hjb2hdb").set("param", "expr");
    model.result().dataset("surfdset2hjb2hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb2.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb2.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb2.Rj))");
    model.result().dataset("surfdset2hjb2hdb").set("expry", "hdb.hjb2.r1");
    model.result().dataset("surfdset2hjb2hdb").set("defaultPlotIDs", new String[]{"pg5|hdb", "pg6|hdb"});
    model.result().dataset("surfdset2hjb2hdb").label("\u8868\u9762 (hjb2)");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb2)");
    model.result("pg7").set("data", "surfdset2hjb2hdb");
    model.result("pg7").set("edges", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg7").feature("surf1").set("colortabletype", "discrete");
    model.result("pg7").feature("surf1").set("expr", "hdb.p");
    model.result("pg7").feature("surf1").create("hght1", "Height");
    model.result("pg7").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb2)");
    model.result("pg7").run();
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").set("method", "pointdir");
    model.result().dataset("mir2").set("pddir", new int[]{1, 0});
    model.result().dataset().duplicate("mir3", "mir2");
    model.result().dataset("mir3").set("data", "surfdset2hjb2hdb");
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result("pg6").run();
    model.result("pg6").set("data", "mir2");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").set("plotarrayenable", true);
    model.result("pg6").set("arrayaxis", "y");
    model.result("pg6").feature("surf1").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").feature("surf2").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("data", "mir3");
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").run();
    model.result("pg1").run();

    model.title("\u5706\u67f1\u5f62\u8f74\u9888\u8f74\u627f\u7684\u5f39\u6d41\u6da6\u6ed1");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5728\u5206\u6790\u91cd\u8f7d\u6d41\u4f53-\u819c\u8f74\u627f\u65f6\u8003\u8651\u5f39\u6027\u53d8\u5f62\u7684\u91cd\u8981\u6027\u3002\u5176\u4e2d\u4f7f\u7528\u201c\u56fa\u4f53\u529b\u5b66\u201d\u63a5\u53e3\u5bf9\u7ed3\u6784\u90e8\u4ef6\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u901a\u8fc7\u201c\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u201d\u63a5\u53e3\u6765\u786e\u5b9a\u6da6\u6ed1\u6cb9\u7684\u538b\u529b\u3002\n\n\u672c\u4f8b\u5047\u8bbe\u8f74\u9888\u548c\u8f74\u627f\u7531\u94dd\u5236\u6210\uff0c\u4e14\u6da6\u6ed1\u6cb9\u6a21\u62df\u5177\u6709\u538b\u529b\u76f8\u5173\u5c5e\u6027\u7684\u901a\u7528\u6cb9\u3002\n\n\u6b64\u5916\uff0c\u8fd8\u5c06\u5f39\u6d41\u6da6\u6ed1\u7684\u7ed3\u679c\u4e0e\u7b80\u5316\u7684\u6d41\u4f53\u52a8\u529b\u5b66\u4eff\u771f\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("elastohydrodynamic_journal_bearing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
