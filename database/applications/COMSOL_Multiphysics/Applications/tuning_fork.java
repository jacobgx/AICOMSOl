/*
 * tuning_fork.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class tuning_fork {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.param().set("Lp", "75[mm]");
    model.param().descr("Lp", "Prong length");
    model.param().set("rb", "5.5[mm]");
    model.param().descr("rb", "Base radius");
    model.param().set("rp", "2.5[mm]");
    model.param().descr("rp", "Prong radius");
    model.param().set("Lh", "40[mm]");
    model.param().descr("Lh", "Handle length");

    model.component("comp1").geom("geom1").repairTolType("relative");
    model.component("comp1").geom("geom1").scaleUnitValue(true);
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "rp");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Lh");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"rb", "0", "-Lh-rb"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "rp*2");
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new String[]{"rb", "0", "-(rb+Lh)"});
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("tor1", "Torus");
    model.component("comp1").geom("geom1").feature("tor1").set("rmaj", "rb");
    model.component("comp1").geom("geom1").feature("tor1").set("rmin", "rp");
    model.component("comp1").geom("geom1").feature("tor1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("tor1").set("pos", new String[]{"rb", "0", "0"});
    model.component("comp1").geom("geom1").feature("tor1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("tor1").set("ax3", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("tor1").set("rot", -90);
    model.component("comp1").geom("geom1").run("tor1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "sph1", "tor1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "rp");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "Lp");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "rp");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "Lp");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"2*rb", "0", "0"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl2", "cyl3", "uni1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showmaterial", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").label("Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat3").label("Copper");
    model.component("comp1").material("mat3").set("family", "copper");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").label("Iron");
    model.component("comp1").material("mat4").set("family", "iron");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"4000", "0", "0", "0", "4000", "0", "0", "0", "4000"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "7870[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.29");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").label("Titanium beta-21S");
    model.component("comp1").material("mat5").set("family", "titanium");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "710[J/(kg*K)]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "4940[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "105[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.33");

    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 2);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(10, 18);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "10[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "2[mm]");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("method", "dellegacy52");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("e1").set("rtol", "1e-6");

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 7);
    model.study("std1").feature("eig").set("shift", "0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("Mode Shape (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "Displacement field");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq").label("Eigenfrequencies (Study 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "Angular frequency", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "Damping ratio", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "Quality factor", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{7});
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("edges", false);

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "0.0005");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "freq", 0);
    model.result().numerical("gev1").setIndex("descr", "Computed fundamental frequency:", 0);
    model.result().numerical("gev1").label("Frequency");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "Lp", 0);
    model.result().numerical("gev2").setIndex("unit", "", 0);
    model.result().numerical("gev2").label("Prong length");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Frequency");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.title(null);

    model.description("");

    model.label("tuning_fork_embedded.mph");

    model.setExpectedComputationTime("4 \u79d2\uff08\u9891\u7387\uff09\uff1b14 \u79d2\uff08\u957f\u5ea6\uff09");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").set("filename", "user:///tuning_fork.docx");
    model.result().report("rpt1").set("numberformat", "custom");
    model.result().report("rpt1").set("precision", 4);
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").label("\u97f3\u53c9");
    model.result().report("rpt1").feature("tp1").set("author", "");
    model.result().report("rpt1").feature("tp1").set("includeauthor", false);
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u901a\u8fc7\u672c App\uff0c\u60a8\u53ef\u4ee5\u6307\u5b9a\u4e00\u4e2a\u97f3\u53c9\u7684\u53c9\u9f7f\u957f\u5ea6\uff0c\u8ba1\u7b97\u5176\u7279\u5f81\u9891\u7387\uff1b\u6216\u8005\u53cd\u8fc7\u6765\uff0c\u6307\u5b9a\u76ee\u6807\u9891\u7387\uff0c\u8ba1\u7b97\u51fa\u76f8\u5e94\u7684\u53c9\u9f7f\u957f\u5ea6\u3002");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").set("heading", "Software Information");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").set("heading", "Model Parameters");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param1")
         .label("\u5185\u5d4c\u6a21\u578b\u7684\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature().create("field1", "BooleanDataField");
    model.result().report("rpt1").feature("sec2").feature("field1").label("\u67e5\u627e");
    model.result().report("rpt1").feature("sec2").feature().create("field2", "DoubleDataField");
    model.result().report("rpt1").feature("sec2").feature("field2")
         .label("\u9891\u7387\u548c\u53c9\u9f7f\u957f\u5ea6");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").set("heading", "Results");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").set("heading", "Plot Groups");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").set("heading", "Derived Values");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature("mtbl1")
         .label("\u8ba1\u7b97\u7684\u9891\u7387");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").set("heading", "Visualization");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("pg1")
         .label("\u632f\u578b");
    model.result("pg1").run();

    model.title("\u97f3\u53c9");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4ee5\u7279\u5b9a\u7684\u8ba1\u7b97\u9891\u7387\u64ad\u653e\u58f0\u97f3\n\u2022 \u4ece\u7ec4\u5408\u6846\u4e2d\u9009\u62e9\u4e0d\u540c\u7684\u6750\u6599\n\u2022 \u4e3a\u8ba1\u7b97\u673a\u3001\u5e73\u677f\u7535\u8111\u6216\u667a\u80fd\u624b\u673a\u9009\u62e9\u4e09\u79cd\u4e0d\u540c\u7684\u7528\u6237\u754c\u9762\u5e03\u5c40\n\u2022 \u6b63\u5272\u6cd5\u7684\u81ea\u5b9a\u4e49\u5b9e\u73b0\n\u2022 \u81ea\u5b9a\u4e49\u7a97\u53e3\u56fe\u6807\u3002\n\n\u5f53\u97f3\u53c9\u53d7\u5230\u6572\u51fb\u65f6\uff0c\u4f1a\u4ee5\u4e00\u79cd\u590d\u6742\u7684\u8fd0\u52a8\u6a21\u5f0f\u632f\u52a8\uff0c\u8fd9\u79cd\u8fd0\u52a8\u6a21\u5f0f\u5728\u6570\u5b66\u4e0a\u53ef\u4ee5\u63cf\u8ff0\u4e3a\u8c10\u632f\u6a21\u6001\u7684\u53e0\u52a0\uff0c\u4ea6\u79f0\u4e3a\u7279\u5f81\u6a21\u6001\u3002\u6bcf\u4e2a\u6a21\u6001\u90fd\u4e0e\u7279\u5b9a\u7684\u7279\u5f81\u9891\u7387\u6709\u5173\u3002\u97f3\u53c9\u901a\u8fc7\u6240\u6709\u7279\u5f81\u9891\u7387\u7684\u7ec4\u5408\u6240\u4ea7\u751f\u7684\u7279\u5b9a\u97f3\u8272\u53d1\u51fa\u72ec\u7279\u7684\u58f0\u97f3\u3002\n\n\u8be5 App \u8ba1\u7b97\u53c9\u957f\u53ef\u6539\u53d8\u7684\u97f3\u53c9\u7684\u56fa\u6709\u8c10\u632f\u9891\u7387\u3002\u6b64\u5916\uff0c\u60a8\u8fd8\u53ef\u4ee5\u63d0\u4f9b\u7528\u6237\u5b9a\u4e49\u7684\u76ee\u6807\u9891\u7387\uff0cApp \u5c06\u4f7f\u7528\u57fa\u4e8e\u6b63\u5272\u6cd5\u7684\u7b97\u6cd5\u627e\u5230\u76f8\u5e94\u7684\u53c9\u957f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("tuning_fork.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
