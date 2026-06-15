/*
 * beam_optimization_milling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:46 by COMSOL 6.3.0.290. */
public class beam_optimization_milling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Topology_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("L0", "1[m]");
    model.param().descr("L0", "Beam length");
    model.param().set("T0", "20[cm]");
    model.param().descr("T0", "Beam height");
    model.param().set("M0", "L0*T0*2700[kg/m^3]");
    model.param().descr("M0", "Beam weight");
    model.param().set("Yopt1", "0[m]");
    model.param().descr("Yopt1", "Y position, point 1");
    model.param().set("Yopt2", "0[m]");
    model.param().descr("Yopt2", "Y position, point 2");
    model.param().set("maxDisp", "0.2[mm]");
    model.param().descr("maxDisp", "Maximum displacement");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L0/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Yopt1", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L0", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Yopt2", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L0", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "0.9*T0", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L0", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "T0", 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L0", 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "T0", 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "T0", 6, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "0.9*T0", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "L0", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "0.9*T0", 1, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("Bottom Boundaries");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "eps");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel2", "boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel2").label("Right Boundaries");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "L0-eps");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").run("fin");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").label("Aluminum");
    model.material("mat1").set("family", "aluminum");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 3);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(5);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "ForceLength");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceLength", new String[]{"0", "-1e6[N/m]*((X/L0)^4*(1-X/L0))", "0"});

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").set("densitySource", "fromPhysics");

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").label("Tip Displacement");
    model.component("comp1").probe("point1").set("probename", "pnt_disp");
    model.component("comp1").probe("point1").selection().set(7);

    model.study("std1").feature("stat").set("probesel", "none");
    model.study("std1").label("Study 1: Parameter Optimization");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("Stress (solid)");
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
    model.result("pg1").feature("surf1").feature("def").set("descr", "Displacement field");
    model.result("pg1").run();
    model.result("pg1").label("Parameter Optimization");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Parameter Optimization - eval(mass1.mass) kg (eval(mass1.mass/M0*100,,%d) %)");
    model.result("pg1").set("titlenumberformat", "auto");
    model.result("pg1").set("titleprecision", 3);
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.disp");
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", 0);
    model.result("pg1").feature("surf1").set("rangecolormax", "1.05*maxDisp");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 100);
    model.result("pg1").run();
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("elemcolor", "none");
    model.result("pg1").feature("mesh1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("mesh1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("mesh1").feature("def1").set("scale", 100);
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "1");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature("def1").set("expr", new String[]{"u+solid.fax", "v+solid.fay"});
    model.result("pg1").feature("line1").feature("def1")
         .set("descr", "Displacement field + Force per deformed area (spatial frame)");
    model.result("pg1").feature("line1").feature("def1").setIndex("expr", "u", 0);
    model.result("pg1").feature("line1").feature("def1").set("expr", new String[]{"u", "v-1e-8*solid.FperLengthy"});
    model.result("pg1").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("line1").feature("def1").set("scale", 100);
    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("expr", new String[]{"solid.FperLengthx", "v"});
    model.result("pg1").feature("arwl1").setIndex("expr", "solid.FperLengthy", 1);
    model.result("pg1").feature("arwl1").set("arrowbase", "head");
    model.result("pg1").feature("arwl1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").set("scale", "1e-6");
    model.result("pg1").feature("arwl1").set("arrowcount", 160);
    model.result("pg1").feature("arwl1").set("color", "black");
    model.result("pg1").feature("arwl1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").feature("def1").set("scale", 100);
    model.result("pg1").run();

    model.study("std1").create("opt", "Optimization");
    model.study("std1").feature("opt").set("optsolver", "cobyla");
    model.study("std1").feature("opt").set("optobj", new String[]{"comp1.mass1.mass"});
    model.study("std1").feature("opt").set("descr", new String[]{"Mass"});
    model.study("std1").feature("opt").set("objectivescaling", "init");
    model.study("std1").feature("opt").setIndex("pname", "L0", 0);
    model.study("std1").feature("opt").setIndex("initval", "1[m]", 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("pname", "L0", 0);
    model.study("std1").feature("opt").setIndex("initval", "1[m]", 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("pname", "T0", 1);
    model.study("std1").feature("opt").setIndex("initval", "20[cm]", 1);
    model.study("std1").feature("opt").setIndex("scale", 1, 1);
    model.study("std1").feature("opt").setIndex("lbound", "", 1);
    model.study("std1").feature("opt").setIndex("ubound", "", 1);
    model.study("std1").feature("opt").setIndex("pname", "T0", 1);
    model.study("std1").feature("opt").setIndex("initval", "20[cm]", 1);
    model.study("std1").feature("opt").setIndex("scale", 1, 1);
    model.study("std1").feature("opt").setIndex("lbound", "", 1);
    model.study("std1").feature("opt").setIndex("ubound", "", 1);
    model.study("std1").feature("opt").setIndex("pname", "Yopt1", 0);
    model.study("std1").feature("opt").setIndex("initval", 0, 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", 0, 0);
    model.study("std1").feature("opt").setIndex("ubound", "0.9*T0", 0);
    model.study("std1").feature("opt").setIndex("pname", "Yopt2", 1);
    model.study("std1").feature("opt").setIndex("initval", 0, 1);
    model.study("std1").feature("opt").setIndex("scale", 1, 1);
    model.study("std1").feature("opt").setIndex("lbound", 0, 1);
    model.study("std1").feature("opt").setIndex("ubound", "0.9*T0", 1);
    model.study("std1").feature("opt").set("constraintExpression", new String[]{"comp1.pnt_disp"});
    model.study("std1").feature("opt").setIndex("constraintExpression", "comp1.pnt_disp/maxDisp", 0);
    model.study("std1").feature("opt").setIndex("constraintUbound", 1, 0);
    model.study("std1").feature("opt").set("plot", true);
    model.study("std1").feature("opt").set("probesel", "none");
    model.study("std1").feature("opt").set("keepsol", "last");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("Parametric Solutions 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("o1").run("compute");

    model.result("pg1").run();

    model.study("std1").feature("opt").set("probewindow", "");

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").common().create("poly1", "PolynomialBoundary");
    model.component("comp1").common("poly1").selection().named("geom1_boxsel1");
    model.component("comp1").common("poly1").set("symmetryContinuity", "Disabled");
    model.component("comp1").common("poly1").set("internalPolynomialContinuity", "Enabled");
    model.component("comp1").common("poly1").set("maximumDisplacement", "0.9*T0");
    model.component("comp1").common().create("fsr1", "FreeShapeSymmetry");
    model.component("comp1").common("fsr1").selection().named("geom1_boxsel2");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("probesel", "none");
    model.study("std2").setGenPlots(false);
    model.study("std2").label("Study 2: Shape Optimization");
    model.study("std2").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("Shape Optimization");
    model.result("pg2").set("title", "Shape Optimization - eval(mass1.mass) kg (eval(mass1.mass/M0*100,,%d) %)");
    model.result("pg2").set("data", "dset4");

    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("mmamaxiter", 20);
    model.study("std2").feature("sho").set("optobj", new String[]{"comp1.mass1.mass"});
    model.study("std2").feature("sho").set("descr", new String[]{"Mass"});
    model.study("std2").feature("sho").set("objectivescaling", "init");
    model.study("std2").feature("sho").set("constraintExpression", new String[]{"comp1.pnt_disp"});
    model.study("std2").feature("sho").setIndex("constraintExpression", "comp1.pnt_disp/maxDisp", 0);
    model.study("std2").feature("sho").setIndex("constraintLbound", "", 0);
    model.study("std2").feature("sho").setIndex("constraintUbound", "", 0);
    model.study("std2").feature("sho").setIndex("constraintUbound", 1, 0);
    model.study("std2").feature("sho").set("probesel", "none");
    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg2");
    model.study("std2").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result("pg2").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result("pg2").run();

    model.component("comp1").common().create("dtopo1", "DensityTopology");
    model.component("comp1").common("dtopo1").selection().all();
    model.component("comp1").common("dtopo1").selection().set(1);
    model.component("comp1").common("dtopo1").set("discretization", "constant");
    model.component("comp1").common("dtopo1").set("theta_0", "1");
    model.component("comp1").common().create("ftopom1", "MaterialTopologyDomain");
    model.component("comp1").common("ftopom1").selection().set(2);

    model.component("comp1").material().create("toplnk1", "TopologyLink");
    model.component("comp1").material("toplnk1").selection().all();
    model.component("comp1").material("toplnk1").set("topologySource", "dtopo1");

    model.component("comp1").mesh().duplicate("mesh2", "mesh1");
    model.component("comp1").mesh("mesh2").autoMeshSize(1);
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("Study 3: Topology Optimization");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std3").feature("stat").set("probesel", "none");
    model.study("std3").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("Topology Optimization");
    model.result("pg3").set("title", "Topology Optimization - eval(mass1.mass) kg (eval(mass1.mass/M0*100,,%d) %)");
    model.result("pg3").set("data", "dset5");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("filt1").set("expr", "0.5<dtopo1.theta");

    model.study("std3").create("topo", "TopologyOptimization");
    model.study("std3").feature("topo").set("mmamaxiter", 50);
    model.study("std3").feature("topo").set("optobj", new String[]{"comp1.mass1.mass"});
    model.study("std3").feature("topo").set("descr", new String[]{"Mass"});
    model.study("std3").feature("topo").set("objectivescaling", "init");
    model.study("std3").feature("topo").setEntry("controlVariableActive", "poly1", false);
    model.study("std3").feature("topo").set("constraintExpression", new String[]{"comp1.pnt_disp"});
    model.study("std3").feature("topo").setIndex("constraintExpression", "comp1.pnt_disp/maxDisp", 0);
    model.study("std3").feature("topo").setIndex("constraintLbound", "", 0);
    model.study("std3").feature("topo").setIndex("constraintUbound", "", 0);
    model.study("std3").feature("topo").setIndex("constraintUbound", 1, 0);
    model.study("std3").feature("topo").set("probesel", "none");
    model.study("std3").feature("topo").set("plot", true);
    model.study("std3").feature("topo").set("plotgroup", "pg3");
    model.study("std3").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result("pg3").run();

    model.study("std3").feature("topo").set("probewindow", "");
    model.study("std1").feature("opt").setEntry("controlVariableActive", "dtopo1.theta_c", false);
    model.study("std1").feature("opt").setEntry("controlVariableActive", "poly1", false);
    model.study("std2").feature("sho").setEntry("controlVariableActive", "dtopo1.theta_c", false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").setSolveFor("/common/dtopo1", false);
    model.study("std1").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").setSolveFor("/common/dtopo1", false);

    model.result("pg1").run();
    model.result().duplicate("pg4", "pg1");
    model.result("pg4").run();
    model.result("pg4").label("Thumbnail");
    model.result("pg4").set("titletype", "none");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").set("showlegends", false);
    model.result("pg4").feature().copy("surf2", "pg2/surf1");
    model.result("pg4").feature().copy("mesh2", "pg2/mesh1");
    model.result("pg4").feature().copy("line2", "pg2/line1");
    model.result("pg4").feature().copy("arwl2", "pg2/arwl1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "dset4");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("def").set("expr", new String[]{"u", "v-2.5e-3"});
    model.result("pg4").run();
    model.result("pg4").feature("mesh2").set("data", "dset4");
    model.result("pg4").run();
    model.result("pg4").feature("mesh2").feature("def1").set("expr", new String[]{"u", "v-2.5e-3"});
    model.result("pg4").run();
    model.result("pg4").feature("line2").set("data", "dset4");
    model.result("pg4").run();
    model.result("pg4").feature("line2").feature("def1")
         .set("expr", new String[]{"u", "-1e-8*solid.FperLengthy+v-2.5e-3"});
    model.result("pg4").run();
    model.result("pg4").feature("arwl2").set("data", "dset4");
    model.result("pg4").run();
    model.result("pg4").feature("arwl2").feature("def1").set("expr", new String[]{"u", "v-2.5e-3"});
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("data", "dset5");
    model.result("pg4").run();
    model.result("pg4").feature().copy("surf3", "pg3/surf1");
    model.result("pg4").feature().copy("mesh3", "pg3/mesh1");
    model.result("pg4").feature().copy("line3", "pg3/line1");
    model.result("pg4").feature().copy("arwl3", "pg3/arwl1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf3").feature("def").set("expr", new String[]{"u", "v-5e-3"});
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg4").run();
    model.result("pg4").feature("mesh3").set("data", "dset5");
    model.result("pg4").run();
    model.result("pg4").feature("mesh3").feature("def1").set("expr", new String[]{"u", "v-5e-3"});
    model.result("pg4").run();
    model.result("pg4").feature("line3").set("data", "dset5");
    model.result("pg4").run();
    model.result("pg4").feature("line3").feature("def1")
         .set("expr", new String[]{"u", "-1e-8*solid.FperLengthy+v-5e-3"});
    model.result("pg4").run();
    model.result("pg4").feature("arwl3").set("data", "dset5");
    model.result("pg4").run();
    model.result("pg4").feature("arwl3").feature("def1").set("expr", new String[]{"u", "v-5e-3"});
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u6881\u7684\u8bbe\u8ba1\u4f18\u5316");

    model
         .description("\u94dd\u6881\u53d7\u4f4d\u79fb\u7ea6\u675f\u548c\u5206\u5e03\u8f7d\u8377\u7684\u5f71\u54cd\uff0c\u5176\u8d28\u91cf\u8fbe\u5230\u6700\u5c0f\u3002\u672c\u4f8b\u4f7f\u7528\u53c2\u6570\u4f18\u5316\u3001\u5f62\u72b6\u4f18\u5316\u548c\u62d3\u6251\u4f18\u5316\u6765\u6c42\u89e3\u8be5\u95ee\u9898\u3002");

    model.label("beam_optimization.mph");

    model.result("pg4").run();

    model.study().remove("std1");
    model.study().remove("std2");

    model.component("comp1").common().remove("fsd1");
    model.component("comp1").common().remove("poly1");
    model.component("comp1").common().remove("fsr1");

    model.component("comp1").mesh().remove("mesh2");

    model.param().set("volfrac", "0.4");
    model.param().descr("volfrac", "\u4f53\u79ef\u5206\u6570");
    model.param().set("meshsz", "1[cm]");
    model.param().descr("meshsz", "\u7f51\u683c\u5927\u5c0f");

    model.component("comp1").common("dtopo1").set("millingActive", "Enabled");
    model.component("comp1").common("dtopo1").setIndex("mil_xVector", 0, 1);
    model.component("comp1").common("dtopo1").setIndex("mil_yVector", 0, 1);
    model.component("comp1").common("dtopo1").setIndex("mil_xVector", 0, 1);
    model.component("comp1").common("dtopo1").setIndex("mil_yVector", 0, 1);
    model.component("comp1").common("dtopo1").setIndex("mil_xVector", 1, 1);
    model.component("comp1").common("dtopo1").set("filterLengthType", "Custom");
    model.component("comp1").common("dtopo1").set("theta_0", "0.1");

    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");

    model.sol("sol5").feature("v1").updateVariables();
    model.sol("sol5").feature("o1").set("gcmma", false);
    model.sol("sol5").feature("o1").feature("s1").create("se1", "Segregated");
    model.sol("sol5").feature("o1").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol5").feature("o1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol5").feature("o1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol5").feature("o1").feature("s1").feature("se1").feature("ss2").label("\u56fa\u4f53\u529b\u5b66");
    model.sol("sol5").feature("o1").feature("s1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_dtopo1_theta_c", "comp1_u"});
    model.sol("sol5").feature("o1").feature("s1").feature("se1").feature("ss1").label("\u94e3\u524a");
    model.sol("sol5").feature("o1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_dtopo1_theta_c", "comp1_dtopo1_theta_m1", "comp1_dtopo1_theta_m2"});
    model.sol("sol5").feature("o1").feature("s1").feature("se1").feature("ssDef").label("\u4f18\u5316");
    model.sol("sol5").feature("o1").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_dtopo1_theta_c", "comp1_dtopo1_theta_f"});

    model.result("pg3").run();
    model.result("pg3").set("title", "\u62d3\u6251\u4f18\u5316\uff08\u94e3\u524a\uff09 - eval(mass1.mass) kg");
    model.result("pg3").set("titlenumberformat", "stopwatch");
    model.result("pg3").set("titledecimals", 0);

    model.study("std3").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result("pg3").run();

    model.study("std3").feature("topo").set("probewindow", "");

    model.title("\u5e26\u94e3\u524a\u7ea6\u675f\u7684\u6881\u7684\u62d3\u6251\u4f18\u5316");

    model
         .description("\u94dd\u6881\u53d7\u4f4d\u79fb\u7ea6\u675f\u548c\u5206\u5e03\u8f7d\u8377\u7684\u5f71\u54cd\uff0c\u5176\u8d28\u91cf\u8fbe\u5230\u6700\u5c0f\u3002\u672c\u4f8b\u4f7f\u7528\u5e26\u94e3\u524a\u7ea6\u675f\u7684\u62d3\u6251\u4f18\u5316\u6765\u6c42\u89e3\u8be5\u95ee\u9898\u3002");

    model.label("beam_optimization_milling.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
