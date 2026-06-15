/*
 * micromechanical_model_of_an_octet_truss_lattice_cell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:12 by COMSOL 6.3.0.290. */
public class micromechanical_model_of_an_octet_truss_lattice_cell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Material_Models");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d", "0.1 [mm]", "\u652f\u67f1\u76f4\u5f84");
    model.param().set("L", "10 [mm]", "\u5355\u5143\u8fb9\u957f");
    model.param().set("V", "L^3", "\u5355\u5143\u4f53\u79ef");
    model.param().set("rhob", "2700 [kg/m^3]", "\u672c\u4f53\u6750\u6599\u5bc6\u5ea6");
    model.param().set("Eb", "70[GPa]", "\u672c\u4f53\u6750\u6599\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nub", "0.3", "\u672c\u4f53\u6750\u6599\u6cca\u677e\u6bd4");
    model.param().set("para", "4", "\u7b2c\u4e00\u4e2a\u7814\u7a76\u4e2d\u7684\u53c2\u6570\u53d8\u91cf");
    model.param().set("m", "3", "\u9635\u5217\u53c2\u6570\uff0cx \u65b9\u5411");
    model.param().set("n", "3", "\u9635\u5217\u53c2\u6570\uff0cy \u65b9\u5411");
    model.param().set("p", "10", "\u9635\u5217\u53c2\u6570\uff0cz \u65b9\u5411");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Lattice_Structures\\lattice_octet_truss.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d", "para*d");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "L", "L");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"Eb"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nub"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhob"});

    model.component("comp1").physics("solid").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid").feature("cp1").set("Volume", "UserDefined");
    model.component("comp1").physics("solid").feature("cp1").set("vol", "V");
    model.component("comp1").physics("solid").feature("cp1").set("computeDensity", true);
    model.component("comp1").physics("solid").feature("cp1").set("computeElasticityMatrixStandard", true);
    model.component("comp1").physics("solid").feature("cp1").set("parametricStudy", "yes");
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterRange", "range(1,0.2,2) 4", 0, 0);
    model.component("comp1").physics("solid").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().named("geom1_pi1_unisel1");
    model.component("comp1").physics("solid").feature("cp1").create("bp2", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().named("geom1_pi1_unisel2");
    model.component("comp1").physics("solid").feature("cp1").create("bp3", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().named("geom1_pi1_unisel3");
    model.component("comp1").physics("solid").feature("cp1").runCommand("createLoadGroupsandStudy");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").named("geom1_pi1_sel1");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").named("geom1_pi1_sel2");
    model.component("comp1").mesh("mesh1").feature().duplicate("id2", "id1");
    model.component("comp1").mesh("mesh1").feature("id2").selection("group1").named("geom1_pi1_sel3");
    model.component("comp1").mesh("mesh1").feature("id2").selection("group2").named("geom1_pi1_sel4");
    model.component("comp1").mesh("mesh1").feature().duplicate("id3", "id2");
    model.component("comp1").mesh("mesh1").feature("id3").selection("group1").named("geom1_pi1_sel5");
    model.component("comp1").mesh("mesh1").feature("id3").selection("group2").named("geom1_pi1_sel6");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("solidcp1std").createAutoSequences("all");

    model.batch("solidcp1p").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").setIndex("looplevel", 7, 1);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("solidcp1stdEg", "EvaluationGroup");
    model.result().evaluationGroup("solidcp1stdEg").set("data", "dset2");
    model.result().evaluationGroup("solidcp1stdEg").set("includeparameters", "off");
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("solidcp1stdEg")
         .label("\u6709\u6548\u6750\u6599\u5c5e\u6027 (\u5355\u5143\u5468\u671f\u6027\u7814\u7a76)");
    model.result().evaluationGroup("solidcp1stdEg").create("gevdcp1", "EvalGlobal");
    model.result().evaluationGroup("solidcp1stdEg").feature("gevdcp1")
         .setIndex("expr", "root.comp1.solid.cp1.rho", 0);
    model.result().evaluationGroup("solidcp1stdEg").feature("gevdcp1").set("descr", "\u5bc6\u5ea6");
    model.result().evaluationGroup("solidcp1stdEg").create("gmevescp1", "EvalGlobalMatrix");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("expr", "root.comp1.solid.cp1.D");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("descr", "\u5f39\u6027\u77e9\u9635");
    model.result().evaluationGroup("solidcp1stdEg").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"solid.cp1.Dinv11"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u67d4\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf"});
    model.result("pg2").feature("glob1").setIndex("unit", "1/GPa", 0);
    model.result("pg2").feature("glob1").set("expr", new String[]{"solid.cp1.Dinv11", "solid.cp1.Dinv12"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u67d4\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf", "\u67d4\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf"});
    model.result("pg2").feature("glob1").setIndex("unit", "1/GPa", 1);
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"solid.cp1.Dinv11", "solid.cp1.Dinv12", "solid.cp1.Dinv44"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u67d4\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf", "\u67d4\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf", "\u67d4\u5ea6\u77e9\u9635\uff0c44 \u5206\u91cf"});
    model.result("pg2").feature("glob1").setIndex("unit", "1/GPa", 2);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "root.comp1.solid.cp1.rho/rhob");
    model.result("pg2").feature("glob1").set("xdatadescractive", true);
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "Computed, 11-component", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "Computed, 12-component", 1);
    model.result("pg2").feature("glob1").setIndex("legends", "Computed, 44-component", 2);
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").setIndex("expr", "(9/(root.comp1.solid.cp1.rho/rhob))/Eb", 0);
    model.result("pg2").feature("glob2").setIndex("unit", "1/GPa", 0);
    model.result("pg2").feature("glob2").setIndex("descr", "Analytical compliance matrix, 11-component", 0);
    model.result("pg2").feature("glob2").setIndex("expr", "-(3/(root.comp1.solid.cp1.rho/rhob))/Eb", 1);
    model.result("pg2").feature("glob2").setIndex("unit", "1/GPa", 1);
    model.result("pg2").feature("glob2").setIndex("descr", "Analytical compliance matrix, 12-component", 1);
    model.result("pg2").feature("glob2").setIndex("expr", "(12/(root.comp1.solid.cp1.rho/rhob))/Eb", 2);
    model.result("pg2").feature("glob2").setIndex("unit", "1/GPa", 2);
    model.result("pg2").feature("glob2").setIndex("descr", "Analytical compliance matrix, 44-component", 2);
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "level2");
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "root.comp1.solid.cp1.rho/rhob");
    model.result("pg2").feature("glob2").set("xdatadescractive", true);
    model.result("pg2").feature("glob2").set("linestyle", "dotted");
    model.result("pg2").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "Analytical, 11-component", 0);
    model.result("pg2").feature("glob2").setIndex("legends", "Analytical, 12-component", 1);
    model.result("pg2").feature("glob2").setIndex("legends", "Analytical, 44-component", 2);
    model.result("pg2").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");

    model.component("comp1").physics("solid").feature("cp1").runCommand("createMaterialbyValue");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("pi1", "PartInstance");
    model.component("comp2").geom("geom2").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom2").feature("pi1").set("part", "part1");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "d", "4*d");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("inputexpr", "L", "L");
    model.component("comp2").geom("geom2").feature("pi1").set("displ", new String[]{"0", "0", "0.5*L"});
    model.component("comp2").geom("geom2").run("pi1");
    model.component("comp2").geom("geom2").create("arr1", "Array");
    model.component("comp2").geom("geom2").feature("arr1").selection("input").set("pi1");
    model.component("comp2").geom("geom2").feature("arr1").set("fullsize", new String[]{"m", "n", "p"});
    model.component("comp2").geom("geom2").feature("arr1").set("displ", new String[]{"L", "L", "L"});
    model.component("comp2").geom("geom2").run("arr1");
    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"m*L", "n*L", "p*L"});
    model.component("comp2").geom("geom2").feature("blk1").set("pos", new String[]{"5*L", "-L/2", "0"});
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").create("boxsel1", "BoxSelection");
    model.component("comp2").geom("geom2").feature("boxsel1").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("boxsel1").set("zmax", 0);
    model.component("comp2").geom("geom2").feature("boxsel1").set("condition", "inside");
    model.component("comp2").geom("geom2").run("boxsel1");
    model.component("comp2").geom("geom2").create("boxsel2", "BoxSelection");
    model.component("comp2").geom("geom2").feature("boxsel2").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("boxsel2").set("zmin", "p*L");
    model.component("comp2").geom("geom2").feature("boxsel2").set("condition", "inside");

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/solid2", true);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("matlnk1", "Link");
    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").selection().named("geom2_pi1_boxsel1");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", new String[]{"Eb"});
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", new String[]{"nub"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", new String[]{"rhob"});

    model.component("comp2").physics("solid2").feature("lemm1").set("SolidModel", "Anisotropic");
    model.component("comp2").physics("solid2").create("fix1", "Fixed", 2);
    model.component("comp2").physics("solid2").feature("fix1").selection().named("geom2_boxsel1");
    model.component("comp2").physics("solid2").create("disp1", "Displacement2", 2);
    model.component("comp2").physics("solid2").feature("disp1").selection().named("geom2_boxsel2");
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("U0", "-10[mm]", 1);

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().set(8982);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection()
         .set(18683, 18685, 18687, 18688);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("numelem", "m");
    model.component("comp2").mesh("mesh2").feature("map1").create("dis2", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").selection()
         .set(18682, 18684, 18690, 18691);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").set("numelem", "n");
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("swe1").selection().set(91);
    model.component("comp2").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("numelem", "p");
    model.component("comp2").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh2").feature("size").set("hauto", 4);

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol8").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").label("\u5e94\u529b (solid2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("unit", "MPa");
    model.result("pg3").run();

    model.component("comp2").view("view3").set("showgrid", false);

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").label("\u4f4d\u79fb (solid2)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid2.disp"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").label("\u4f4d\u79fb (solid2)");
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset4");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().set(8982);
    model.result().evaluationGroup("eg1").feature("int1").set("expr", new String[]{"solid2.RFy"});
    model.result().evaluationGroup("eg1").feature("int1")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\uff0cy \u5206\u91cf"});
    model.result().evaluationGroup("eg1").feature("int1").set("method", "summation");
    model.result().evaluationGroup("eg1").create("int2", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int2").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int2").selection()
         .set(3, 254, 525, 2986, 3237, 3508, 5969, 6220, 6491);
    model.result().evaluationGroup("eg1").feature("int2").set("expr", new String[]{"solid2.RFy"});
    model.result().evaluationGroup("eg1").feature("int2")
         .set("descr", new String[]{"\u53cd\u4f5c\u7528\u529b\uff0cy \u5206\u91cf"});
    model.result().evaluationGroup("eg1").feature("int2").set("method", "summation");
    model.result().evaluationGroup("eg1").run();
    model.result("pg3").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 2);
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").add("plotgroup", "pg4");

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/solid2", false);

    model.result("pg3").run();

    model.title("\u516b\u89d2\u6841\u67b6\u6676\u683c\u5355\u5143\u7684\u5fae\u89c2\u529b\u5b66\u6a21\u578b");

    model
         .description("\u5229\u7528\u6676\u683c\u6750\u6599\uff0c\u53ef\u4ee5\u521b\u5efa\u5177\u6709\u5b9a\u5236\u673a\u68b0\u5c5e\u6027\u7684\u5148\u8fdb\u589e\u6750\u5236\u9020\u6750\u6599\u3002\u5728\u5b8f\u89c2\u5c42\u9762\uff0c\u8fd9\u4e9b\u5f02\u8d28\u6750\u6599\u53ef\u4ee5\u6a21\u62df\u4e3a\u5747\u8d28\u6750\u6599\u3002\u901a\u8fc7\u5747\u8d28\u5316\u6280\u672f\uff0c\u6211\u4eec\u80fd\u591f\u6839\u636e\u6676\u683c\u7ed3\u6784\u53ca\u5176\u7ec4\u6210\u90e8\u5206\u7684\u7279\u6027\uff0c\u786e\u5b9a\u51c6\u786e\u7684\u6750\u6599\u6709\u6548\u5c5e\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("solidcp1sol").clearSolutionData();
    model.sol("solidcp1solp").clearSolutionData();
    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("micromechanical_model_of_an_octet_truss_lattice_cell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
