/*
 * general_parameter_estimation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:39 by COMSOL 6.3.0.290. */
public class general_parameter_estimation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("xparam", "0");
    model.param().set("xmin", "2");
    model.param().set("xmax", "14");
    model.param().set("tol", "0.001");
    model.param().set("conf", "0.95");
    model.param().set("par1", "0");
    model.param().set("par2", "0");
    model.param().set("par3", "0");
    model.param().set("par4", "0");
    model.param().set("par5", "0");
    model.param().set("par1min", "-Inf");
    model.param().set("par2min", "-Inf");
    model.param().set("par3min", "-Inf");
    model.param().set("par4min", "-Inf");
    model.param().set("par5min", "-Inf");
    model.param().set("par1max", "Inf");
    model.param().set("par2max", "Inf");
    model.param().set("par3max", "Inf");
    model.param().set("par4max", "Inf");
    model.param().set("par5max", "Inf");
    model.param().set("par1scale", "1");
    model.param().set("par2scale", "1");
    model.param().set("par3scale", "1");
    model.param().set("par4scale", "1");
    model.param().set("par5scale", "1");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("expr", "par1*x+par2");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "xmin", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "xmax", 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "1e-3*(xmax-xmin)");
    model.component("comp1").mesh("mesh1").run();

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("general_parameter_estimation_embedded_data.txt");
    model.result().table().create("tbl2", "Table");

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").set("source", "resultTable");
    model.component("comp1").common("lso1").setEntry("columnType", "col1", "param");
    model.component("comp1").common("lso1").setEntry("parameterName", "col1", "xparam");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col2", "par1*xparam+par2");
    model.component("comp1").common("lso1").setEntry("scaleMethod", "col2", "manual");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("opt", "Optimization");
    model.study("std1").feature("opt").set("optsolver", "lm");
    model.study("std1").feature("opt").set("opttolinner", "tol");
    model.study("std1").feature("opt").setIndex("pname", "xparam", 0);
    model.study("std1").feature("opt").setIndex("initval", 0, 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("pname", "xparam", 0);
    model.study("std1").feature("opt").setIndex("initval", 0, 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("pname", "xmin", 1);
    model.study("std1").feature("opt").setIndex("initval", 2, 1);
    model.study("std1").feature("opt").setIndex("scale", 1, 1);
    model.study("std1").feature("opt").setIndex("lbound", "", 1);
    model.study("std1").feature("opt").setIndex("ubound", "", 1);
    model.study("std1").feature("opt").setIndex("pname", "xmin", 1);
    model.study("std1").feature("opt").setIndex("initval", 2, 1);
    model.study("std1").feature("opt").setIndex("scale", 1, 1);
    model.study("std1").feature("opt").setIndex("lbound", "", 1);
    model.study("std1").feature("opt").setIndex("ubound", "", 1);
    model.study("std1").feature("opt").setIndex("pname", "par1", 0);
    model.study("std1").feature("opt").setIndex("pname", "par2", 1);
    model.study("std1").feature("opt").set("addconfint", true);
    model.study("std1").feature("opt").set("conflevel", "conf");
    model.study("std1").feature("opt").set("confinttable", "tbl2");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat")
         .set("plistarr", new String[]{"2.0000000000000000e+00 2.2448979591836733e+00 2.4897959183673470e+00 2.7346938775510203e+00 2.9795918367346940e+00 3.2244897959183674e+00 3.4693877551020407e+00 3.7142857142857144e+00 3.9591836734693877e+00 4.2040816326530610e+00 4.4489795918367350e+00 4.6938775510204085e+00 4.9387755102040810e+00 5.1836734693877550e+00 5.4285714285714290e+00 5.6734693877551020e+00 5.9183673469387750e+00 6.1632653061224490e+00 6.4081632653061230e+00 6.6530612244897960e+00 6.8979591836734695e+00 7.1428571428571420e+00 7.3877551020408160e+00 7.6326530612244900e+00 7.8775510204081630e+00 8.1224489795918370e+00 8.3673469387755100e+00 8.6122448979591830e+00 8.8571428571428580e+00 9.1020408163265300e+00 9.3469387755102030e+00 9.5918367346938780e+00 9.8367346938775500e+00 1.0081632653061224e+01 1.0326530612244898e+01 1.0571428571428571e+01 1.0816326530612246e+01 1.1061224489795919e+01 1.1306122448979592e+01 1.1551020408163264e+01 1.1795918367346939e+01 1.2040816326530614e+01 1.2285714285714285e+01 1.2530612244897960e+01 1.2775510204081632e+01 1.3020408163265307e+01 1.3265306122448980e+01 1.3510204081632653e+01 1.3755102040816325e+01 1.4000000000000000e+01"});
    model.study("std1").feature("stat").set("pname", new String[]{"xparam"});
    model.study("std1").createAutoSequences("all");
    model.study("std1").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std1").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std1").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std1").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol1").runAll();

    model.study("std1").feature("opt").set("probewindow", "");

    model.result().dataset().create("mesh1", "Mesh");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linecolor", "red");
    model.result("pg1").feature("tblp1").set("linemarker", "square");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "Data", 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").set("data", "mesh1");
    model.result("pg1").feature("lngr1").selection().all();
    model.result("pg1").feature("lngr1").set("expr", "par1*x+par2");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("linestyle", "dashed");
    model.result("pg1").feature("lngr1").set("linecolor", "black");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "Initial Model", 0);
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("expr", "0.39883*x+2.9880");
    model.result("pg1").feature("lngr2").set("linestyle", "solid");
    model.result("pg1").feature("lngr2").set("linecolor", "blue");
    model.result("pg1").feature("lngr2").setIndex("legends", "Optimized Model", 0);
    model.result("pg1").feature("lngr2").active(false);

    model.study("std1").feature("opt").set("optsolver", "bobyqa");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("Parametric Solutions 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("o1").run("compute");

    model.result("pg1").run();

    model.study("std1").feature("opt").set("probewindow", "");

    model.result("pg1").run();
    model.result("pg1").feature("lngr2").active(true);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").active(true);
    model.result().table().remove("tbl3");

    model.title(null);

    model.description("");

    model.label("general_parameter_estimation_embedded.mph");

    model.sol("sol1").copySolution("sol4");

    model.study("std1").feature("opt").set("optsolver", "lm");

    model.sol("sol4").createAutoSequence("std1");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///general_data_fitting.docx");
    model.result().report("rpt1").set("pagebreaklevel", "2");
    model.result().report("rpt1").set("enumlevel", "none");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("title", "\u901a\u7528\u53c2\u6570\u4f30\u8ba1");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature("tp1").set("frontmatterlayout", "headings");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includeusedproducts", false);
    model.result().report("rpt1").feature("sec1").feature("root1").set("includename", false);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u6c42\u89e3\u5668");
    model.result().report("rpt1").feature("sec2").feature().create("txt1", "Text");
    model.result().report("rpt1").feature("sec2").feature("txt1")
         .set("text", "\u6c42\u89e3\u5668\u7c7b\u578b\uff1aLevenberg-Marquardt\n\u5bb9\u5dee\uff1a0.001\n\u6a21\u578b\u6700\u5927\u8ba1\u7b97\u6b21\u6570\uff1a1000\n\u7f6e\u4fe1\u5ea6\uff1a0.95\n\u6700\u5c0f\u5316\uff1a\u6c42\u548c\n\u51fd\u6570\uff1apar1*x+par2\n\n\u53c2\u6570\u7684\u521d\u59cb\u503c\uff0c\u6bd4\u4f8b\uff08\u4e0b\u754c\u3001\u4e0a\u754c\uff09\n0, 1 (,)\n0, 1 (,)");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("mtbl1").set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec3").feature().create("pg1", "PlotGroup");

    model.title("\u4e00\u822c\u53c2\u6570\u4f30\u8ba1");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4ece\u6587\u672c\u6587\u4ef6\u5bfc\u5165\u6d4b\u91cf\u6570\u636e\uff0c\u6216\u4f7f\u7528\u5185\u7f6e\u529f\u80fd\u6765\u751f\u6210\u6570\u636e\n\u2022 \u6839\u636e\u8f93\u5165\u81ea\u52a8\u66f4\u6539\u6c42\u89e3\u5668\u9009\u9879\n\u2022 \u52a8\u6001\u66f4\u65b0\u65b9\u7a0b\u663e\u793a\n\n\u8be5 App \u53ef\u7528\u4e8e\u4f30\u8ba1\u4e0d\u542b\u4efb\u4f55\u7269\u7406\u573a\u7684\u6a21\u578b\u7684\u53c2\u6570\u3002\u7528\u6237\u65e2\u53ef\u4ee5\u4ece\u6587\u4ef6\u5bfc\u5165\u6570\u636e\uff0c\u4e5f\u53ef\u4ee5\u5229\u7528\u5185\u7f6e\u529f\u80fd\u6765\u751f\u6210\u6570\u636e\u3002\n\n\u8fd9\u4e9b\u6a21\u578b\u5305\u62ec\u7ebf\u6027\u3001\u4e8c\u6b21\u3001sigmoid\u3001\u503e\u659c\u9ad8\u65af\uff0c\u4ee5\u53ca\u5177\u6709\u591a\u8fbe 5 \u4e2a\u53c2\u6570\u7684\u5b9a\u5236\u6a21\u578b\u3002\n\nLevenberg-Marquardt \u6c42\u89e3\u5668\u7528\u4e8e\u8ba1\u7b97\u5df2\u4f30\u8ba1\u53c2\u6570\u7684\u7f6e\u4fe1\u533a\u95f4\uff0c\u800c\u5176\u4ed6\u6c42\u89e3\u5668\uff08MMA\u3001SNOPT\u3001IPOPT \u548c BOBYQA\uff09\u5219\u652f\u6301\u6307\u5b9a\u53c2\u6570\u754c\u9650\uff0cMMA \u548c BOBYQA \u652f\u6301\u5c06\u6700\u5927\u5e73\u65b9\uff08\u800c\u975e\u603b\u548c\uff09\u6700\u5c0f\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("general_parameter_estimation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
