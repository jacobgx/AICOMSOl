/*
 * dna_degradation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:18 by COMSOL 6.3.0.290. */
public class dna_degradation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Ideal_Tank_Reactors");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("k1", "1e-3[1/s]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("k2", "1e-3[1/s]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("k3", "1e-3[1/s]", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570");
    model.param().set("c_SC_init", "10[ng/ul]/M_pDNA", "\u521d\u59cb\u6d53\u5ea6");
    model.param().set("M_pDNA", "1.95e6[g/mol]", "\u5206\u5b50\u91cf");
    model.param().set("c_H2O_init", "1000[kg/m^3]/18[g/mol]", "\u521d\u59cb\u6d53\u5ea6");

    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "SC=>OC");
    model.component("comp1").physics("re").feature("rch1").set("kf", "k1");
    model.component("comp1").physics("re").feature("SC").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("re").feature("OC").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "OC=>L");
    model.component("comp1").physics("re").feature("rch2").set("kf", "k2");
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "L=>F");
    model.component("comp1").physics("re").feature("rch3").set("kf", "k3");
    model.component("comp1").physics("re").feature("F").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("re").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_H2O_init", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_SC_init", 4, 0);

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").set("filename", "dna_degradation_experiment1.csv");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col2", "re.c_SC*M_pDNA");
    model.component("comp1").common("lso1").setEntry("variableName", "col2", "SC");
    model.component("comp1").common("lso1").setEntry("unit", "col2", "ng/ul");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col3", "re.c_OC*M_pDNA");
    model.component("comp1").common("lso1").setEntry("variableName", "col3", "OC");
    model.component("comp1").common("lso1").setEntry("unit", "col3", "ng/ul");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col4", "re.c_L*M_pDNA");
    model.component("comp1").common("lso1").setEntry("variableName", "col4", "L");
    model.component("comp1").common("lso1").setEntry("unit", "col4", "ng/ul");

    model.study("std1").feature("time").set("tlist", "0 3600");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_SC", "re.c_OC", "re.c_L", "re.c_F"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();

    model.study("std1").create("lsqo", "LSQOptimization");
    model.study("std1").feature("lsqo").setIndex("pname", "k1", 0);
    model.study("std1").feature("lsqo").setIndex("initval", "1e-3[1/s]", 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "k1", 0);
    model.study("std1").feature("lsqo").setIndex("initval", "1e-3[1/s]", 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "k2", 1);
    model.study("std1").feature("lsqo").setIndex("initval", "1e-3[1/s]", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("pname", "k2", 1);
    model.study("std1").feature("lsqo").setIndex("initval", "1e-3[1/s]", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("pname", "k3", 2);
    model.study("std1").feature("lsqo").setIndex("initval", "1e-3[1/s]", 2);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std1").feature("lsqo").setIndex("pname", "k3", 2);
    model.study("std1").feature("lsqo").setIndex("initval", "1e-3[1/s]", 2);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std1").feature("lsqo").setIndex("pname", "c_SC_init", 3);
    model.study("std1").feature("lsqo").setIndex("initval", "10[ng/ul]/M_pDNA", 3);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std1").feature("lsqo").setIndex("pname", "c_SC_init", 3);
    model.study("std1").feature("lsqo").setIndex("initval", "10[ng/ul]/M_pDNA", 3);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std1").feature("lsqo").setIndex("scale", "1e-3", 0);
    model.study("std1").feature("lsqo").setIndex("lbound", 0, 0);
    model.study("std1").feature("lsqo").setIndex("scale", "1e-3", 1);
    model.study("std1").feature("lsqo").setIndex("lbound", 0, 1);
    model.study("std1").feature("lsqo").setIndex("scale", "1e-3", 2);
    model.study("std1").feature("lsqo").setIndex("lbound", 0, 2);
    model.study("std1").feature("lsqo").setIndex("scale", "10[ng/ul]/M_pDNA", 3);
    model.study("std1").feature("lsqo").setIndex("lbound", 0, 3);
    model.study("std1").feature("lsqo").set("opttolinner", "0.0001");

    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6d53\u5ea6 (kg/m<sup>3</sup>)");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").label("\u4eff\u771f\u6570\u636e");
    model.result("pg1").feature("glob1").setIndex("expr", "re.c_SC*M_pDNA", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "SC", 0);
    model.result("pg1").feature("glob1").setIndex("expr", "re.c_OC*M_pDNA", 1);
    model.result("pg1").feature("glob1").setIndex("descr", "OC", 1);
    model.result("pg1").feature("glob1").setIndex("expr", "re.c_L*M_pDNA", 2);
    model.result("pg1").feature("glob1").setIndex("descr", "L", 2);
    model.result("pg1").feature("glob1").setIndex("expr", "", 3);
    model.result("pg1").feature("glob1").set("linestyle", "cycle");
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("linecolor", "blue");
    model.result("pg1").feature("glob1").set("autodescr", true);
    model.result("pg1").feature("glob1").set("autosolution", false);
    model.result("pg1").feature("glob1").set("autoexpr", false);
    model.result("pg1").feature("glob1").set("legendprefix", "\u4eff\u771f ");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("dna_degradation_experiment1.csv");
    model.result("pg1").run();
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").label("\u5b9e\u9a8c\u6570\u636e");
    model.result("pg1").feature("tblp1").set("preprocy", "linear");
    model.result("pg1").feature("tblp1").set("scalingy", "1/1000");
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linemarker", "cycle");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u5b9e\u9a8c SC", 0);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u5b9e\u9a8c OC", 1);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u5b9e\u9a8c L", 2);

    model.study("std1").feature("lsqo").set("plot", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study("std1").feature("lsqo").set("probewindow", "");

    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u8840\u6d46\u4e2d\u7684 DNA \u964d\u89e3");

    model
         .description("\u57fa\u56e0\u7597\u6cd5\u4e2d\u4e00\u4e2a\u4e3b\u8981\u96be\u9898\u662f\u5982\u4f55\u5c06\u8d28\u4f53 DNA \u8f93\u9001\u5230\u76ee\u6807\u3002\u672c\u4f8b\u4f7f\u7528\u53c2\u6570\u4f30\u8ba1\u6765\u786e\u5b9a\u5178\u578b DNA \u964d\u89e3\u8fc7\u7a0b\u4e2d\u6d89\u53ca\u7684\u4e09\u4e2a\u8fde\u9501\u53cd\u5e94\u7684\u901f\u7387\u5e38\u6570\u3002\n\n\u6b64\u6a21\u578b\u9700\u8981\u201c\u4f18\u5316\u6a21\u5757\u201d\u3002");

    model.label("dna_degradation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
