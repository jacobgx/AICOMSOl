/*
 * ishigami_function_uncertainty_quantification.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:34 by COMSOL 6.3.0.290. */
public class ishigami_function_uncertainty_quantification {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Uncertainty_Quantification_Module\\Tutorials");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");

    model.param().set("X1", "1");
    model.param().descr("X1", "\u968f\u673a\u53d8\u91cf 1");
    model.param().set("X2", "1");
    model.param().descr("X2", "\u968f\u673a\u53d8\u91cf 2");
    model.param().set("X3", "1");
    model.param().descr("X3", "\u968f\u673a\u53d8\u91cf 3");
    model.param().set("a", "7");
    model.param().descr("a", "Ishigami \u53c2\u6570 1");
    model.param().set("b", "0.1");
    model.param().descr("b", "Ishigami \u53c2\u6570 2");
    model.param().set("M", "a/2");
    model.param().descr("M", "\u5e73\u5747\u503c");
    model.param().set("V", "(a^2)/8+b*(pi^4)/5+b^2*(pi^8)/18+1/2");
    model.param().descr("V", "\u65b9\u5dee");
    model.param().set("STD", "sqrt(V)");
    model.param().descr("STD", "\u6807\u51c6\u5dee");
    model.param().set("V1", "0.5*(1+b*(pi^4)/5)^2");
    model.param().descr("V1", "\u4e00\u9636\u65b9\u5dee 1");
    model.param().set("V2", "(a^2)/8");
    model.param().descr("V2", "\u4e00\u9636\u65b9\u5dee 2");
    model.param().set("V3", "0");
    model.param().descr("V3", "\u4e00\u9636\u65b9\u5dee 3");
    model.param().set("S1", "V1/V");
    model.param().descr("S1", "\u4e00\u9636 Sobol \u6307\u6570 1");
    model.param().set("S2", "V2/V");
    model.param().descr("S2", "\u4e00\u9636 Sobol \u6307\u6570 2");
    model.param().set("S3", "V3/V");
    model.param().descr("S3", "\u4e00\u9636 Sobol \u6307\u6570 3");
    model.param().set("VT1", "(1/2)*(1+b*(pi^4)/5)^2+(8*b^2*pi^8)/225");
    model.param().descr("VT1", "\u603b\u65b9\u5dee 1");
    model.param().set("VT2", "(a^2)/8");
    model.param().descr("VT2", "\u603b\u65b9\u5dee 2");
    model.param().set("VT3", "(8*b^2*pi^8)/225");
    model.param().descr("VT3", "\u603b\u65b9\u5dee 3");
    model.param().set("ST1", "VT1/V");
    model.param().descr("ST1", "\u603b Sobol \u6307\u6570 1");
    model.param().set("ST2", "VT2/V");
    model.param().descr("ST2", "\u603b Sobol \u6307\u6570 2");
    model.param().set("ST3", "VT3/V");
    model.param().descr("ST3", "\u603b Sobol \u6307\u6570 3");
    model.param().set("imax", "8+(pi^4)/10");
    model.param().descr("imax", "\u51fd\u6570 max");
    model.param().set("imin", "-1-(pi^4)/10");
    model.param().descr("imin", "\u51fd\u6570 min");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "ishigami");
    model.func("an1").set("expr", "sin(x1)+a*(sin(x2))^2+b*x3^4*sin(x1)");
    model.func("an1").set("args", "x1,x2,x3");
    model.func("an1").label("Ishigami \u51fd\u6570");
    model.func("an1").setIndex("plotargs", "-pi", 0, 1);
    model.func("an1").setIndex("plotargs", "pi", 0, 2);
    model.func("an1").setIndex("plotargs", "-pi", 1, 1);
    model.func("an1").setIndex("plotargs", "pi", 1, 2);
    model.func("an1").setIndex("plotargs", "-pi", 2, 1);
    model.func("an1").setIndex("plotargs", "pi", 2, 2);
    model.func("an1").createPlot("pg1");

    model.result("pg1").run();

    model.study("std1").create("uq", "UncertaintyQuantification");
    model.study("std1").feature("uq").setIndex("funcname", "", 0);
    model.study("std1").feature("uq").setIndex("qoiexpression", "", 0);
    model.study("std1").feature("uq").setIndex("qoisolutionindv", "parent", 0);
    model.study("std1").feature("uq").setIndex("failif", "larger", 0);
    model.study("std1").feature("uq").setIndex("threshold", "", 0);
    model.study("std1").feature("uq").setIndex("funcname", "", 0);
    model.study("std1").feature("uq").setIndex("qoiexpression", "", 0);
    model.study("std1").feature("uq").setIndex("qoisolutionindv", "parent", 0);
    model.study("std1").feature("uq").setIndex("failif", "larger", 0);
    model.study("std1").feature("uq").setIndex("threshold", "", 0);
    model.study("std1").feature("uq").setIndex("qoiexpression", "ishigami(X1,X2,X3)", 0);
    model.study("std1").feature("uq").setIndex("pname", "X1", 0);
    model.study("std1").feature("uq").setEntry("sourceType", "col1", "analytic");
    model.study("std1").feature("uq").setIndex("paramDescription", "", 0);
    model.study("std1").feature("uq").setIndex("pname", "X1", 0);
    model.study("std1").feature("uq").setEntry("sourceType", "col1", "analytic");
    model.study("std1").feature("uq").setIndex("paramDescription", "", 0);
    model.study("std1").feature("uq").setIndex("pname", "X2", 1);
    model.study("std1").feature("uq").setEntry("sourceType", "col2", "analytic");
    model.study("std1").feature("uq").setIndex("paramDescription", "", 1);
    model.study("std1").feature("uq").setIndex("pname", "X2", 1);
    model.study("std1").feature("uq").setEntry("sourceType", "col2", "analytic");
    model.study("std1").feature("uq").setIndex("paramDescription", "", 1);
    model.study("std1").feature("uq").setIndex("pname", "X3", 2);
    model.study("std1").feature("uq").setEntry("sourceType", "col3", "analytic");
    model.study("std1").feature("uq").setIndex("paramDescription", "", 2);
    model.study("std1").feature("uq").setIndex("pname", "X3", 2);
    model.study("std1").feature("uq").setEntry("sourceType", "col3", "analytic");
    model.study("std1").feature("uq").setIndex("paramDescription", "", 2);
    model.study("std1").feature("uq").setEntry("lboundselection", "col1", "-pi");
    model.study("std1").feature("uq").setEntry("uboundselection", "col1", "pi");
    model.study("std1").feature("uq").setEntry("lboundselection", "col2", "-pi");
    model.study("std1").feature("uq").setEntry("uboundselection", "col2", "pi");
    model.study("std1").feature("uq").setEntry("lboundselection", "col3", "-pi");
    model.study("std1").feature("uq").setEntry("uboundselection", "col3", "pi");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pd1").feature("so1").set("psol", "sol2");
    model.batch("uq1").run("compute");

    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").feature().copy("uq", "std1/uq", "");
    model.study("std2").feature().copy("stat", "std1/stat", "");
    model.study("std2").feature("uq").set("uqtype", "screening");
    model.study("std2").feature("uq").set("uqresultgrp", "new");
    model.study("std2").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std2").feature("uq").set("uqresultgrp", "new");
    model.study("std2").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std2").feature("uq").set("uqresultgrp", "new");
    model.study("std2").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std2").feature("uq").set("uqresultgrp", "new");
    model.study("std2").feature("uq").set("uqtype", "inverseuq");
    model.study("std2").feature("uq").set("uqresultgrp", "new");
    model.study("std2").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std2").feature("uq").set("computeaction", "recompute");
    model.study("std2").feature("uq").set("designtable", "new");
    model.study("std2").feature("uq").set("verifyaction", "recompute");
    model.study("std2").feature("uq").set("tablegraphgrp", "new");
    model.study("std2").feature("uq").set("surrogatetol", "0.0005");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std2");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("pd2").feature("so1").set("psol", "sol5");
    model.batch("uq2").run("compute");

    model.result("pg3").run();
    model.result("pg3").run();

    model.study().create("std3");
    model.study("std3").feature().copy("uq", "std2/uq", "");
    model.study("std3").feature().copy("stat", "std2/stat", "");
    model.study("std3").feature("uq").set("uqtype", "screening");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "sensitivityanalysis");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "uncertaintypropagation");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "reliabilityanalysis");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "inverseuq");
    model.study("std3").feature("uq").set("uqresultgrp", "new");
    model.study("std3").feature("uq").set("uqtype", "uncertaintypropagation");

    model.func().create("gpm1", "GaussianProcess");
    model.func("gpm1").active(false);

    model.study("std3").feature("uq").set("globalgpfunction", "gpm1");

    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8")
         .addRows(new double[][]{{0.25614764144518354, 0.11092376061695486, 2.611590014267364, 1.517689242088584}, {-2.4739636053496734, 2.8873629187511822, 1.4927275425551532, -0.4837582272000604}, {1.5575455644300185, -2.0521299242342073, 2.2668510989328396, 9.139881201269604}, {-1.7517924941658967, 2.3768091024984814, -0.4323290248881335, 2.3686373230813436}, {-1.4133921425014058, -0.37573280878847104, -2.5557815354589435, -4.259029445944947}, {0.6714135055272097, 3.0473316775638786, -1.3275093624559497, 0.8773046903448016}, {-0.511869741822649, -2.1948528295192613, 3.0289818494841363, -0.002908652356061303}, {2.4885797852773734, -0.9685666945424414, -1.930237304898545, 6.2047026337406805}, {1.8842262611421727, -2.9968471011985445, -1.8385406825864459, 2.183850480270171}, {-1.1289134205376938, -2.7915991454156908, -2.137953654879564, -1.9695103580954285}, {-2.6771885582201995, -2.391475200706399, 1.141835396905961, 2.7292146277910567}, {0.14794157446261025, 1.9930426752958192, 1.453823753979866, 6.037639176392568}, {-2.192780012101952, -1.187470048141304, -0.7350640413344789, 5.18437787974095}, {2.698492858733382, 0.2716762395331793, 1.7094990525159295, 1.2989715983649255}, {2.048099525878354, 1.83626615858992, -3.0265372102399724, 14.859085857213758}, {-1.6024326132648463, 1.5907399589206195, 2.8978187478214936, -1.050309560733269}, {2.969332468929351, -1.4938294485290158, 0.15853338287137175, 7.130034861480357}, {1.1106297832102863, -2.690222920528122, 0.28539544263466476, 2.2284568646298255}, {2.759632836487138, 2.1592848540761267, -0.19211811409178692, 5.215803943190917}, {1.4393797914336002, 0.9135454235078093, -1.2537151317169295, 5.623545504611694}, {-0.8647884982729535, 0.4406965727450407, -0.3516060836993087, 0.5116102124565368}, {0.6164851772773892, -0.5425918150565114, -2.7279182977816796, 5.646252656961016}, {-0.10120654120396111, -1.440280978358714, -1.0296487662774303, 6.769045920644543}, {2.109838469039105, 2.6902876559789677, 2.0735908927873217, 3.7763791832905143}, {-0.3912708941518539, 1.453330358131173, -2.3654728826505864, 5.328473467465798}, {-2.8828982863572983, 0.6364394060438125, 0.4379220914429647, 2.215885243308576}, {-0.7006393485180848, -1.7212990969789235, 0.9935379350500417, 6.135109129205626}, {0.840117824470572, -0.12359873191309445, 0.6341107492938751, 0.8631556248415231}, {-3.0166839345968453, 1.0482406696607711, -1.6416938675143051, 5.04123914199329}, {-2.068374473707693, -0.7766256589864318, 2.472962335830257, -0.7266250589650056}, {2.2142889353085566, 1.7220830407974468, -1.7284497798486147, 8.355045516109184}, {-1.4824724118815158, -1.1099229679411597, 1.9452414955221062, 3.193144041146061}, {-2.3197326944119743, 2.6078238308965522, -0.09086499281143778, 1.0795919142605659}, {2.3927499878437732, -1.9349017270576772, -1.0565254678728149, 6.877900775791404}, {-0.9832650817552939, 1.4860351143746495, -0.8394560865532918, 6.07618622665067}, {-0.17205892948775547, 0.40336211074841444, -2.8301470181294985, -0.1911664588255595}, {2.527842001757369, 2.3030777497639425, 1.2061945756062382, 4.5689674308683905}, {0.047290183246862405, -0.44948807712000205, -0.3029901600257081, 1.3688716303357238}, {-2.8190142733991745, -1.6274250409078226, 1.2883325929211153, 6.573228157977393}, {-0.7835411358967339, -2.5137604899036825, -0.6820362535002658, 1.694138263799975}, {0.3434215249114465, 0.6244720226586384, 0.8635740058250283, 2.748302285836454}, {-1.2916849390946128, 0.7971093614439435, 0.8013320817437761, 2.5810324766105737}, {1.243916852764099, -1.8612263981209352, 3.134662500140389, 16.51700359858645}, {-1.868981957656474, -0.025562914170664808, -2.475213586046064, -4.539278170000153}, {-0.21694153623282508, 1.9401355513903473, -2.292765691679622, 5.277714748644159}, {0.9998445654650165, -0.7087670329856888, -1.4019417123842681, 4.132090775470475}, {0.464884938549182, 2.7607981492121283, 2.7793394877185955, 4.090417514337707}, {-2.5967453685633473, 0.02610914204213044, 1.6063088134857315, -0.8585706334718941}, {-1.2530536138976294, -2.2510295896211705, 0.03298032642924609, 3.280795376189725}, {3.1275457067848826, -2.916550793484984, -2.0212523515714933, 0.38605406843501877}, {-3.0892193292136656, -0.9362032913481859, -1.5497974458411976, 4.457153866123339}, {2.8824140365874964, 1.3255516382967185, 0.5968321648106065, 6.8468971102950285}, {1.3483973352367284, 1.2221854566350903, -2.653853408257927, 11.996703567570755}, {0.7987630548662161, -2.446214653214434, 2.100953483087765, 4.985728044769594}, {1.7194753387916126, -0.24002700648098285, 0.3464230442119427, 1.3859970183987678}, {1.576103910556614, 3.006332812937016, -0.5637408864668938, 1.137373169148368}, {1.941230547336997, -3.120623278976455, 2.3746551648905916, 3.8993791233476904}, {-2.274087362476478, -1.2869654303884321, 2.6646866198150176, 1.8428818006407308}, {-0.5836947547135707, 2.419108531908969, -3.1009921769191777, -2.586489989661633}, {-1.899175842845688, 1.0092397784076104, 1.8400024403474688, 2.9835204880298267}});

    model.func("gpm1").set("source", "resultTable");
    model.func("gpm1").set("resultTable", "tbl8");
    model.func("gpm1").set("ignorenaninf", true);

    model.study("std3").feature("uq").set("designtable", "tbl8");

    model.func("gpm1").set("covfunction", "matern32");
    model.func("gpm1").set("meanfunction", "const");
    model.func("gpm1").set("improvementfunction", "entropy");
    model.func("gpm1").set("lastinternalseed", 1014);
    model.func("gpm1").set("gpadpoptmethod", "direct");
    model.func("gpm1").set("maxgpevals", 10000);
    model.func("gpm1").set("maxgpiters", 500);
    model.func("gpm1").set("adpevals", 10000);
    model.func("gpm1").set("setupfromstudy", "on");
    model.func("gpm1").setEntry("lboundselection", "col1", "-3.141592653589793");
    model.func("gpm1").setEntry("uboundselection", "col1", "3.141592653589793");
    model.func("gpm1").setEntry("lcdfselection", "col1", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col1", "manual");
    model.func("gpm1").setEntry("lboundselection", "col2", "-3.141592653589793");
    model.func("gpm1").setEntry("uboundselection", "col2", "3.141592653589793");
    model.func("gpm1").setEntry("lcdfselection", "col2", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col2", "manual");
    model.func("gpm1").setEntry("lboundselection", "col3", "-3.141592653589793");
    model.func("gpm1").setEntry("uboundselection", "col3", "3.141592653589793");
    model.func("gpm1").setEntry("lcdfselection", "col3", "manual");
    model.func("gpm1").setEntry("ucdfselection", "col3", "manual");
    model.func("gpm1").setEntry("args", "col1", "X1");
    model.func("gpm1").setEntry("columnType", "col2", "arg");
    model.func("gpm1").setEntry("args", "col2", "X2");
    model.func("gpm1").setEntry("columnType", "col3", "arg");
    model.func("gpm1").setEntry("args", "col3", "X3");
    model.func("gpm1").setEntry("funcs", "col4", "gpm1_1");

    model.study("std3").feature("uq").set("computeaction", "append");
    model.study("std3").feature("uq").set("tablegraphgrp", "new");
    model.study("std3").feature("uq").set("surrogatemodel", "aspce");
    model.study("std3").feature("uq").set("computeaction", "analysis");
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std3");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("pd3").feature("so1").set("psol", "sol8");
    model.batch("uq3").run("compute");

    model.result("pg4").run();
    model.result("pg4").run();

    model.title("Ishigami \u51fd\u6570\u7684\u4e0d\u786e\u5b9a\u6027\u91cf\u5316");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9 Ishigami \u51fd\u6570\u6267\u884c\u4e0d\u786e\u5b9a\u6027\u91cf\u5316\u5206\u6790\uff0c\u5176\u4e2d\u4e09\u4e2a\u53d8\u91cf\u7684\u968f\u673a\u51fd\u6570\u662f\u4e00\u4e2a\u8457\u540d\u7684\u57fa\u51c6\u51fd\u6570\uff0c\u7528\u4e8e\u6d4b\u8bd5\u5168\u5c40\u7075\u654f\u5ea6\u5206\u6790\u548c\u4e0d\u786e\u5b9a\u6027\u91cf\u5316\u7b97\u6cd5\u3002\u5bf9\u4e8e\u672c\u4f8b\u4f7f\u7528\u7684\u8f93\u5165\u5206\u5e03\uff0c\u53ef\u4ee5\u901a\u8fc7\u5206\u6790\u8ba1\u7b97\u5f97\u5230 Ishigami \u51fd\u6570\u7684\u5e73\u5747\u503c\u3001\u6807\u51c6\u5dee\u3001\u6700\u5927\u503c\u3001\u6700\u5c0f\u503c\u548c Sobol \u6307\u6570\u3002");

    model.label("ishigami_function_uncertainty_quantification.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
