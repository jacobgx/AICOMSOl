/*
 * lumped_composite_thermal_barrier.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:35 by COMSOL 6.3.0.290. */
public class lumped_composite_thermal_barrier {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Thin_Structure");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.param().set("d_ceram1", "50[um]");
    model.param().descr("d_ceram1", "\u5c42 1 \u7684\u539a\u5ea6");
    model.param().set("d_ceram2", "75[um]");
    model.param().descr("d_ceram2", "\u5c42 2 \u7684\u539a\u5ea6");
    model.param().set("k_ceram1", "1[W/(m*K)]");
    model.param().descr("k_ceram1", "\u5c42 1 \u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("k_ceram2", "0.5[W/(m*K)]");
    model.param().descr("k_ceram2", "\u5c42 2 \u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("T_hot", "1220[degC]");
    model.param().descr("T_hot", "\u9ad8\u6e29");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 4);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "d_ceram1");
    model.component("comp1").geom("geom1").feature("cyl2")
         .set("pos", new String[]{"0", "0", "2-(d_ceram1+d_ceram2)/2"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "d_ceram2");
    model.component("comp1").geom("geom1").feature("cyl3")
         .set("pos", new String[]{"0", "0", "2-(d_ceram1+d_ceram2)/2+d_ceram1"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -2, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 4, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 4, 1, 2);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("matlnk1", "Link");
    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").label("Steel AISI 4340");
    model.material("mat1").set("family", "steel");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("matlnk1").set("link", "mat1");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().set(2);
    model.material().create("mat2", "Common", "");
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.material("mat2").label("\u9676\u74f7 1");
    model.material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"k_ceram1"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"6000[kg/m^3]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"320[J/(kg*K)]"});
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").selection().set(3);
    model.material().create("mat3", "Common", "");
    model.component("comp1").material("matlnk3").set("link", "mat3");
    model.material("mat3").label("\u9676\u74f7 2");
    model.material("mat3").propertyGroup("def").set("thermalconductivity", new String[]{"k_ceram2"});
    model.material("mat3").propertyGroup("def").set("density", new String[]{"5800[kg/m^3]"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"280[J/(kg*K)]"});

    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(3);
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp2").selection().set(13);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "T_hot");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(13, 18);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u4e09\u7ef4\u65b9\u6cd5");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").label("\u6e29\u5ea6\uff0c\u4e09\u7ef4\u65b9\u6cd5");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").physics().create("ht2", "HeatTransfer", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ht2", false);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics().create("lts", "LumpedThermalSystem", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/lts", false);
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/lts", true);

    model.component("comp2").geom("geom2").lengthUnit("cm");
    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", 2);
    model.component("comp2").geom("geom2").feature("cyl1").set("h", "2-(d_ceram1+d_ceram2)/2");
    model.component("comp2").geom("geom2").run("cyl1");
    model.component("comp2").geom("geom2").create("cyl2", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl2").set("r", 2);
    model.component("comp2").geom("geom2").feature("cyl2").set("h", "2-(d_ceram1+d_ceram2)/2");
    model.component("comp2").geom("geom2").feature("cyl2")
         .set("pos", new String[]{"0", "0", "2+(d_ceram1+d_ceram2)/2"});
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").run("cyl2");
    model.component("comp2").geom("geom2").create("pol1", "Polygon");
    model.component("comp2").geom("geom2").feature("pol1").set("source", "table");
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", -2, 0, 1);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 4, 0, 2);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 2, 1, 1);
    model.component("comp2").geom("geom2").feature("pol1").setIndex("table", 4, 1, 2);
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").view("view2").set("transparency", false);

    model.component("comp2").material().create("matlnk4", "Link");

    model.component("comp2").physics("lts").create("R1", "ConductiveThermalResistor", -1);
    model.component("comp2").physics("lts").feature("R1").label("\u9676\u74f7 1");
    model.component("comp2").physics("lts").feature("R1").set("resistorType", "GeometricProperties");
    model.component("comp2").physics("lts").feature("R1").set("matlist", "mat2");
    model.component("comp2").physics("lts").feature("R1").set("A", "pi*(2[cm])^2");
    model.component("comp2").physics("lts").feature("R1").set("L", "d_ceram1");
    model.component("comp2").physics("lts").feature().duplicate("R2", "R1");
    model.component("comp2").physics("lts").feature("R2").label("\u9676\u74f7 2");
    model.component("comp2").physics("lts").feature("R2").set("matlist", "mat3");
    model.component("comp2").physics("lts").feature("R2").set("L", "d_ceram2");
    model.component("comp2").physics("lts").feature("R2").setIndex("Connections", 1, 0, 0);
    model.component("comp2").physics("lts").feature("R2").setIndex("Connections", 2, 1, 0);
    model.component("comp2").physics("lts").create("term1", "ExternalTerminal", -1);
    model.component("comp2").physics("lts").feature("term1").set("Connections", 0);
    model.component("comp2").physics("lts").create("term2", "ExternalTerminal", -1);
    model.component("comp2").physics("lts").feature("term2").set("Connections", 2);
    model.component("comp2").physics("ht2").create("thermc1", "ThermalConnection", -1);
    model.component("comp2").physics("ht2").feature("thermc1").selection("selectionConnector1").set(4);
    model.component("comp2").physics("ht2").feature("thermc1").selection("selectionConnector2").set(7);
    model.component("comp2").physics("ht2").feature("thermc1").set("connectionType", "LumpedThermalSystem");
    model.component("comp2").physics("ht2").feature("thermc1")
         .set("P_connect2_src", "root.comp2.lts.term2.P_connect_connector_2");
    model.component("comp2").physics("ht2").create("temp1", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht2").feature("temp1").selection().set(3);
    model.component("comp2").physics("ht2").create("temp2", "TemperatureBoundary", 2);
    model.component("comp2").physics("ht2").feature("temp2").selection().set(8);
    model.component("comp2").physics("ht2").feature("temp2").set("T0", "T_hot");

    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().set(8, 11);
    model.component("comp2").mesh("mesh2").run("ftri1");
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").label("\u7814\u7a76 2\uff1a\u96c6\u603b\u70ed\u7cfb\u7edf\u65b9\u6cd5");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht2)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u6e29\u5ea6 (lts)");
    model.result().evaluationGroup("eg1").set("data", "dset3");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").set("data", "dset3");
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u70ed\u8017\u7387 (lts)");
    model.result().evaluationGroup("eg2").set("data", "dset3");
    model.result().evaluationGroup("eg2").set("transpose", true);
    model.result().evaluationGroup("eg2").set("data", "dset3");
    model.result().evaluationGroup("eg1").feature().create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("showsolutionparams", "on");
    model.result().evaluationGroup("eg1").feature("gev1").set("solutionparams", "parent");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"lts.R1.p1.T"});
    model.result().evaluationGroup("eg1").feature("gev1").set("showsolutionparams", "on");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "parent");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"lts.R1.p2.T"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u6e29\u5ea6 p1 (R1)", "\u6e29\u5ea6 p2 (R1)"});
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"lts.R1.p1.T", "lts.R1.p2.T"});
    model.result().evaluationGroup("eg2").feature().create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").set("showsolutionparams", "on");
    model.result().evaluationGroup("eg2").feature("gev1").set("solutionparams", "parent");
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{"lts.R1.P"});
    model.result().evaluationGroup("eg2").feature("gev1").set("showsolutionparams", "on");
    model.result().evaluationGroup("eg2").feature("gev1").set("data", "parent");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"lts.R2.p1.T"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u6e29\u5ea6 p1 (R1)", "\u6e29\u5ea6 p2 (R1)", "\u6e29\u5ea6 p1 (R2)"});
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"lts.R2.p2.T"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u6e29\u5ea6 p1 (R1)", "\u6e29\u5ea6 p2 (R1)", "\u6e29\u5ea6 p1 (R2)", "\u6e29\u5ea6 p2 (R2)"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("expr", new String[]{"lts.R1.p1.T", "lts.R1.p2.T", "lts.R2.p1.T", "lts.R2.p2.T"});
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg2").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{"lts.R2.P"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("descr", new String[]{"\u70ed\u8017\u7387 (R1)", "\u70ed\u8017\u7387 (R2)"});
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{"lts.R1.P", "lts.R2.P"});
    model.result("pg2").run();
    model.result("pg2").label("\u6e29\u5ea6\uff0c\u96c6\u603b\u70ed\u7cfb\u7edf\u65b9\u6cd5");

    model.component().create("comp3", true);

    model.component("comp3").geom().create("geom3", 3);
    model.component("comp3").geom("geom3").geomRep("comsol");

    model.component("comp3").mesh().create("mesh3");
    model.component("comp3").mesh("mesh3").contribute("geom/detail", true);

    model.component("comp3").physics().create("ht3", "HeatTransfer", "geom3");

    model.study("std1").feature("stat").setSolveFor("/physics/ht3", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht3", false);

    model.component("comp3").geom("geom3").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht2", false);
    model.study("std3").feature("stat").setSolveFor("/physics/lts", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht3", true);

    model.component("comp3").geom("geom3").lengthUnit("cm");
    model.component("comp3").geom("geom3").create("cyl1", "Cylinder");
    model.component("comp3").geom("geom3").feature("cyl1").set("r", 2);
    model.component("comp3").geom("geom3").feature("cyl1").set("h", "2-(d_ceram1+d_ceram2)/2");
    model.component("comp3").geom("geom3").run("cyl1");
    model.component("comp3").geom("geom3").create("cyl2", "Cylinder");
    model.component("comp3").geom("geom3").feature("cyl2").set("r", 2);
    model.component("comp3").geom("geom3").feature("cyl2").set("h", "2-(d_ceram1+d_ceram2)/2");
    model.component("comp3").geom("geom3").feature("cyl2")
         .set("pos", new String[]{"0", "0", "2+(d_ceram1+d_ceram2)/2"});
    model.component("comp3").geom("geom3").run("fin");
    model.component("comp3").geom("geom3").run("cyl2");
    model.component("comp3").geom("geom3").create("pol1", "Polygon");
    model.component("comp3").geom("geom3").feature("pol1").set("source", "table");
    model.component("comp3").geom("geom3").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp3").geom("geom3").feature("pol1").setIndex("table", -2, 0, 1);
    model.component("comp3").geom("geom3").feature("pol1").setIndex("table", 4, 0, 2);
    model.component("comp3").geom("geom3").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp3").geom("geom3").feature("pol1").setIndex("table", 2, 1, 1);
    model.component("comp3").geom("geom3").feature("pol1").setIndex("table", 4, 1, 2);
    model.component("comp3").geom("geom3").run("fin");

    model.component("comp3").material().create("matlnk5", "Link");

    model.component("comp3").physics("ht3").create("thermc1", "ThermalConnection", -1);
    model.component("comp3").physics("ht3").feature("thermc1").selection("selectionConnector1").set(4);
    model.component("comp3").physics("ht3").feature("thermc1").selection("selectionConnector2").set(7);
    model.component("comp3").physics("ht3").feature("thermc1")
         .set("R", "(d_ceram1/k_ceram1+d_ceram2/k_ceram2)/(pi*(2[cm])^2)");
    model.component("comp3").physics("ht3").create("temp1", "TemperatureBoundary", 2);
    model.component("comp3").physics("ht3").feature("temp1").selection().set(3);
    model.component("comp3").physics("ht3").create("temp2", "TemperatureBoundary", 2);
    model.component("comp3").physics("ht3").feature("temp2").selection().set(8);
    model.component("comp3").physics("ht3").feature("temp2").set("T0", "T_hot");

    model.component("comp3").mesh("mesh3").create("ftri1", "FreeTri");
    model.component("comp3").mesh("mesh3").feature("ftri1").selection().set(8, 11);
    model.component("comp3").mesh("mesh3").run("ftri1");
    model.component("comp3").mesh("mesh3").create("swe1", "Sweep");
    model.component("comp3").mesh("mesh3").run();

    model.study("std3").label("\u7814\u7a76 3\uff1a\u70ed\u963b\u5668\u8fde\u63a5\u65b9\u6cd5");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht3)");
    model.result("pg3").set("data", "dset6");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6\uff0c\u70ed\u963b\u5668\u8fde\u63a5\u65b9\u6cd5");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6e29\u5ea6\u66f2\u7ebf");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u9ad8\u5ea6 (cm)");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u6e29\u5ea6\u66f2\u7ebf");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(15, 17, 19, 21);
    model.result("pg4").feature("lngr1").set("data", "dset1");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "z");
    model.result("pg4").feature("lngr1").set("linewidth", 2);
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u6e29\u5ea6\uff0c\u4e09\u7ef4\u65b9\u6cd5", 0);
    model.result("pg4").run();
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr2").set("linewidth", "preference");
    model.result("pg4").feature("lngr2").set("data", "dset3");
    model.result("pg4").feature("lngr2").selection().set(11, 14);
    model.result("pg4").feature("lngr2").set("expr", "T2");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").set("xdataexpr", "z");
    model.result("pg4").feature("lngr2").set("linestyle", "none");
    model.result("pg4").feature("lngr2").set("linecolor", "magenta");
    model.result("pg4").feature("lngr2").set("linemarker", "asterisk");
    model.result("pg4").feature("lngr2").set("markerpos", "interp");
    model.result("pg4").feature("lngr2").set("markers", 30);
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").setIndex("legends", "\u6e29\u5ea6\uff0c\u96c6\u603b\u70ed\u7cfb\u7edf", 0);
    model.result("pg4").run();
    model.result("pg4").create("lngr3", "LineGraph");
    model.result("pg4").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr3").set("linewidth", "preference");
    model.result("pg4").feature("lngr3").set("data", "dset6");
    model.result("pg4").feature("lngr3").selection().set(11, 14);
    model.result("pg4").feature("lngr3").set("expr", "T3");
    model.result("pg4").feature("lngr3").set("xdata", "expr");
    model.result("pg4").feature("lngr3").set("xdataexpr", "z");
    model.result("pg4").feature("lngr3").set("linestyle", "none");
    model.result("pg4").feature("lngr3").set("linemarker", "circle");
    model.result("pg4").feature("lngr3").set("linecolor", "green");
    model.result("pg4").feature("lngr3").set("markerpos", "interp");
    model.result("pg4").feature("lngr3").set("markers", 30);
    model.result("pg4").feature("lngr3").set("legend", true);
    model.result("pg4").feature("lngr3").set("legendmethod", "manual");
    model.result("pg4").feature("lngr3")
         .setIndex("legends", "\u6e29\u5ea6\uff0c\u70ed\u963b\u5668\u70ed\u8fde\u63a5", 0);
    model.result("pg4").run();

    model.component("comp1").label("\u7ec4\u4ef6 1\uff1a\u4e09\u7ef4\u65b9\u6cd5");
    model.component("comp2").label("\u7ec4\u4ef6 2\uff1a\u96c6\u603b\u70ed\u7cfb\u7edf\u65b9\u6cd5");
    model.component("comp3").label("\u7ec4\u4ef6 3\uff1a\u70ed\u963b\u5668\u8fde\u63a5\u65b9\u6cd5");

    model.result("pg1").run();

    model.title("\u590d\u5408\u4fdd\u6e29\u5c42\u96c6\u603b\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u662f\u201c\u590d\u5408\u4fdd\u6e29\u5c42\u201d\u6559\u7a0b\u7684\u4e00\u79cd\u53d8\u4f53\uff0c\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u4e09\u79cd\u4e0d\u540c\u7684\u65b9\u5f0f\u6765\u5efa\u7acb\u5177\u6709\u4e0d\u540c\u70ed\u5bfc\u7387\u7684\u591a\u4e2a\u8584\u5939\u5c42\u7ed3\u6784\u3002\n\u9996\u5148\uff0c\u5c06\u590d\u5408\u6750\u6599\u4f5c\u4e3a\u4e09\u7ef4\u5bf9\u8c61\u8fdb\u884c\u5efa\u6a21\u3002\n\u5176\u6b21\uff0c\u91c7\u7528\u201c\u96c6\u603b\u70ed\u7cfb\u7edf\u201d\u7269\u7406\u573a\u63a5\u53e3\uff0c\u901a\u8fc7\u70ed\u7535\u8def\u5efa\u6a21\u6765\u907f\u514d\u89e3\u6790\u8584\u57df\u3002\n\u6700\u540e\uff0c\u4ec5\u4f7f\u7528\u201c\u70ed\u8fde\u63a5\u201d\u7279\u5f81\uff0c\u901a\u8fc7\u627f\u8f7d\u8584\u5c42\u7b49\u6548\u70ed\u963b\u7684\u5355\u4e2a\u7535\u963b\u6765\u6a21\u62df\u7b49\u6548\u96c6\u603b\u7cfb\u7edf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("lumped_composite_thermal_barrier.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
