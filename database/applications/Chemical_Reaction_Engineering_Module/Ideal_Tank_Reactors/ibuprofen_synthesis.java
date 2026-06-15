/*
 * ibuprofen_synthesis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:18 by COMSOL 6.3.0.290. */
public class ibuprofen_synthesis {

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
    model.param().set("cB_0", "0.1[mol/m^3]", "B \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cCO_0", "1.1[mol/m^3]", "CO \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cClion_0", "0.2[mol/m^3]", "Cl- \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cHion_0", "0.2[mol/m^3]", "H+ \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cH2O_0", "3[mol/m^3]", "H2O \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cpd1_0", "1.21e-2[mol/m^3]", "pdl \u521d\u59cb\u6d53\u5ea6");
    model.param().set("croh_0", "1.23[mol/m^3]", "roh \u521d\u59cb\u6d53\u5ea6");
    model.param().set("kreac_1", "7.45e-3[m^3/(s*mol)]", "\u53cd\u5e94 1 \u7684\u53cd\u5e94\u5e38\u6570");
    model.param().set("kreac_2", "1.25e-2[m^6/(s*mol^2)]", "\u53cd\u5e94 2 \u7684\u53cd\u5e94\u5e38\u6570");
    model.param().set("kreac_3", "1.60e-3[m^3/(s*mol)]", "\u53cd\u5e94 3 \u7684\u53cd\u5e94\u5e38\u6570");
    model.param().set("kreac_4", "1.5e-1[m^6/(s*mol^2)]", "\u53cd\u5e94 4 \u7684\u53cd\u5e94\u5e38\u6570");
    model.param().set("kreac_5", "1.59[m^3/(s*mol)]", "\u53cd\u5e94 5 \u7684\u53cd\u5e94\u5e38\u6570");
    model.param().set("kreac_6", "2.14e-1[m^3/(s*mol)]", "\u53cd\u5e94 6 \u7684\u53cd\u5e94\u5e38\u6570");
    model.param().set("kreac_7", "9.52e-1[m^3/(s*mol)]", "\u53cd\u5e94 7 \u7684\u53cd\u5e94\u5e38\u6570");
    model.param().set("kfreac_8", "5e-1[m^6/(s*mol^2)]", "\u53cd\u5e94 8 \u7684\u6b63\u53cd\u5e94\u5e38\u6570");
    model.param()
         .set("krreac_8", "1e-2[m^6/(s*mol^2)]", "\u53cd\u5e94 8 \u7684\u53ef\u9006\u53cd\u5e94\u5e38\u6570");

    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "roh+H+=>ren+H2O+H+");
    model.component("comp1").physics("re").feature("rch1").set("kf", "kreac_1");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "ren+H++Cl-=>rhcl");
    model.component("comp1").physics("re").feature("rch2").set("kf", "kreac_2");
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "rhcl+B=>ren+H++Cl-+B");
    model.component("comp1").physics("re").feature("rch3").set("kf", "kreac_3");
    model.component("comp1").physics("re").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch4").set("formula", "pd1+CO+H2O=>pd2+Cl-+2H++CO2");
    model.component("comp1").physics("re").feature("rch4").set("kf", "kreac_4");
    model.component("comp1").physics("re").create("rch5", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch5").set("formula", "pd2+rhcl=>pd3");
    model.component("comp1").physics("re").feature("rch5").set("kf", "kreac_5");
    model.component("comp1").physics("re").create("rch6", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch6").set("formula", "pd3+CO=>pd4+Cl-");
    model.component("comp1").physics("re").feature("rch6").set("kf", "kreac_6");
    model.component("comp1").physics("re").create("rch7", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch7").set("formula", "pd4+H2O=>pd2+H++ibu");
    model.component("comp1").physics("re").feature("rch7").set("kf", "kreac_7");
    model.component("comp1").physics("re").create("rch8", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch8").set("formula", "ibu+roh+H+<=>ester+H2O+H+");
    model.component("comp1").physics("re").feature("rch8").set("kf", "kfreac_8");
    model.component("comp1").physics("re").feature("rch8").set("kr", "krreac_8");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cB_0", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cCO_0", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cClion_0", 3, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cHion_0", 4, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cH2O_0", 5, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cpd1_0", 8, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "croh_0", 14, 0);

    model.study("std1").label("\u6848\u4f8b 1");
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,3)");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"re/rch8", "re/ester"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1")
         .set("unit", new String[]{"", "", "", "", "", "", "", "", "", "", 
         "", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_roh", "re.c_H_1p", "re.c_ren", "re.c_H2O", "re.c_Cl_1m", "re.c_rhcl", "re.c_B", "re.c_pd1", "re.c_CO", "re.c_pd2", 
         "re.c_CO2", "re.c_pd3", "re.c_pd4", "re.c_ibu"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", 
         "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("\u6848\u4f8b 1 - \u6d53\u5ea6");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("linemarker", "cyclereset");
    model.result("pg1").feature("glob1").set("markers", 5);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "roh", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "H<sup>+</sup>", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "ren", 2);
    model.result("pg1").feature("glob1").setIndex("legends", "H<sub>2</sub>O", 3);
    model.result("pg1").feature("glob1").setIndex("legends", "Cl<sup>-</sup>", 4);
    model.result("pg1").feature("glob1").setIndex("legends", "rhcl", 5);
    model.result("pg1").feature("glob1").setIndex("legends", "B", 6);
    model.result("pg1").feature("glob1").setIndex("legends", "pd1", 7);
    model.result("pg1").feature("glob1").setIndex("legends", "CO", 8);
    model.result("pg1").feature("glob1").setIndex("legends", "pd2", 9);
    model.result("pg1").feature("glob1").setIndex("legends", "CO<sub>2</sub>", 10);
    model.result("pg1").feature("glob1").setIndex("legends", "pd3", 11);
    model.result("pg1").feature("glob1").setIndex("legends", "pd4", 12);
    model.result("pg1").feature("glob1").setIndex("legends", "ibu", 13);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6848\u4f8b 1 - \u6d53\u5ea6\u3001\u4ea7\u7269\u548c\u4e2d\u95f4\u7269\u8d28");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").set("legendpos", "middleright");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{});
    model.result("pg2").feature("glob1").set("descr", new String[]{});
    model.result("pg2").feature("glob1").setIndex("expr", "re.c_roh", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "re.c_ren", 1);
    model.result("pg2").feature("glob1").setIndex("expr", "re.c_rhcl", 2);
    model.result("pg2").feature("glob1").setIndex("expr", "re.c_ibu", 3);
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "roh", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "ren", 1);
    model.result("pg2").feature("glob1").setIndex("legends", "rhcl", 2);
    model.result("pg2").feature("glob1").setIndex("legends", "ibu", 3);
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").set("expr", new String[]{});
    model.result("pg2").feature("glob2").set("descr", new String[]{});
    model.result("pg2").feature("glob2").setIndex("expr", "re.c_pd3", 0);
    model.result("pg2").feature("glob2").setIndex("expr", "re.c_pd4", 1);
    model.result("pg2").feature("glob2").set("linewidth", 2);
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "pd3", 0);
    model.result("pg2").feature("glob2").setIndex("legends", "pd4", 1);
    model.result("pg2").run();
    model.result("pg2").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/re", true);
    model.study("std2").label("\u6848\u4f8b 2");
    model.study("std2").feature("time").set("tunit", "h");
    model.study("std2").feature("time").set("tlist", "range(0,0.1,6)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1")
         .set("unit", new String[]{"", "", "", "", "", "", "", "", "", "", 
         "", "", "", "", ""});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"re.c_roh", "re.c_H_1p", "re.c_ren", "re.c_H2O", "re.c_Cl_1m", "re.c_rhcl", "re.c_B", "re.c_pd1", "re.c_CO", "re.c_pd2", 
         "re.c_CO2", "re.c_pd3", "re.c_pd4", "re.c_ibu", "re.c_ester"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", 
         "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg3").label("\u6d53\u5ea6 (re)");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").run();
    model.result("pg3").label("\u6848\u4f8b 2 - \u6d53\u5ea6");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("legendlayout", "outside");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("linemarker", "cyclereset");
    model.result("pg3").feature("glob1").set("markers", 5);
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "roh", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "H<sup>+</sup>", 1);
    model.result("pg3").feature("glob1").setIndex("legends", "ren", 2);
    model.result("pg3").feature("glob1").setIndex("legends", "H<sub>2</sub>O", 3);
    model.result("pg3").feature("glob1").setIndex("legends", "Cl<sup>-</sup>", 4);
    model.result("pg3").feature("glob1").setIndex("legends", "rhcl", 5);
    model.result("pg3").feature("glob1").setIndex("legends", "B", 6);
    model.result("pg3").feature("glob1").setIndex("legends", "pd1", 7);
    model.result("pg3").feature("glob1").setIndex("legends", "CO", 8);
    model.result("pg3").feature("glob1").setIndex("legends", "pd2", 9);
    model.result("pg3").feature("glob1").setIndex("legends", "CO<sub>2</sub>", 10);
    model.result("pg3").feature("glob1").setIndex("legends", "pd3", 11);
    model.result("pg3").feature("glob1").setIndex("legends", "pd4", 12);
    model.result("pg3").feature("glob1").setIndex("legends", "ibu", 13);
    model.result("pg3").feature("glob1").setIndex("legends", "ester", 14);
    model.result("pg3").run();
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u6848\u4f8b 2 - \u6d53\u5ea6\u3001\u4ea7\u7269\u548c\u4e2d\u95f4\u7269\u8d28");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "re.c_ester", 4);
    model.result("pg4").feature("glob1").setIndex("legends", "ester", 4);
    model.result("pg4").run();
    model.result("pg2").run();

    model.title("\u5e03\u6d1b\u82ac\u5408\u6210");

    model
         .description("\u5e03\u6d1b\u82ac\u662f\u4e00\u79cd\u91cd\u8981\u7684\u6d88\u708e\u836f\uff0c\u901a\u8fc7\u94af\u50ac\u5316\u5242\u5408\u6210\u3002\u672c\u4f8b\u63cf\u8ff0\u4e86\u5b8c\u5168\u6df7\u5408\u95f4\u6b47\u5f0f\u53cd\u5e94\u5668\u4e2d\u590d\u6742\u53cd\u5e94\u673a\u7406\u7684\u52a8\u529b\u5b66\u5206\u6790\u3002");

    model.label("ibuprofen_synthesis.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
