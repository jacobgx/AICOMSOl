/*
 * surface_micromachined_accelerometer_geom.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:05 by COMSOL 6.3.0.290. */
public class surface_micromachined_accelerometer_geom {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", "2m");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid");
    model.component("comp1").physics("es").feature("ccns1").selection().all();

    model.component("comp1").multiphysics().create("eme1", "ElectromechanicalForces", 3);
    model.component("comp1").multiphysics("eme1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("eme1").set("Electrostatics_physics", "es");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().set();
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/eme1", true);

    model.geom()
         .load(new String[]{"part1", "part2", "part3"}, "surface_micromachined_accelerometer_geom_subsequence.mph", new String[]{"part1", "part2", "part3"});

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("acceleration", "0", "\u52a0\u901f\u5ea6 (g)");
    model.param().set("VtestL", "0[V]", "\u6d4b\u8bd5\u7535\u538b\uff0c\u5de6\u4fa7");
    model.param().set("VtestR", "0[V]", "\u6d4b\u8bd5\u7535\u538b\uff0c\u53f3\u4fa7");
    model.param().set("tSi", "2[um]", "\u7845\u539a\u5ea6");
    model.param().set("tOx", "1.6[um]", "\u6c27\u5316\u5c42\u539a\u5ea6");
    model.param().set("l_PM", "448[um]", "\u68c0\u6d4b\u8d28\u91cf\u5757\u957f\u5ea6");
    model.param().set("w_PM", "100[um]", "\u68c0\u6d4b\u8d28\u91cf\u5757\u5bbd\u5ea6");
    model.param().set("n_st", "3", "\u81ea\u68c0\u6307\u7684\u6570\u91cf");
    model.param().set("n_f", "21", "\u611f\u5e94\u6307\u7684\u6570\u91cf");
    model.param().set("w_f", "4[um]", "\u6307\u5bbd");
    model.param().set("l_f", "114[um]", "\u6307\u957f");
    model.param().set("g_f", "1[um]", "\u6307\u95f4\u9699");
    model.param().set("g_st", "3[um]", "\u81ea\u68c0\u6307\u95f4\u9699");
    model.param().set("x_st", "3[um]+1*(w_f+g_st)", "\u81ea\u68c0\u6307\u8d77\u59cb\u4f4d\u7f6e");
    model.param().set("x_f", "(l_PM-(n_f-1)*3*(w_f+g_f)-w_f)/2", "\u611f\u5e94\u6307\u8d77\u59cb\u4f4d\u7f6e");
    model.param().set("w_eh", "4[um]", "\u523b\u8680\u5b54\u5927\u5c0f");
    model.param().set("p_eh", "18[um]", "\u523b\u8680\u5b54\u5468\u671f");
    model.param().set("l_sp", "280[um]", "\u5f39\u7c27\u957f\u5ea6");
    model.param().set("w_sp", "2[um]", "\u5f39\u7c27\u5bbd\u5ea6");
    model.param().set("g_sp", "1[um]", "\u5f39\u7c27\u95f4\u9699");
    model.param().set("w_sp_conn", "4[um]", "\u5f39\u7c27\u8fde\u63a5\u5bbd\u5ea6");
    model.param().set("l_anch_base", "17[um]", "\u951a\u5e95\u5ea7\u957f\u5ea6");
    model.param().set("w_anch_base", "17[um]", "\u951a\u5e95\u5ea7\u5bbd\u5ea6");
    model.param().set("r_anch", "3[um]", "\u951a\u534a\u5f84");
    model.param().set("x_anch", "12[um]", "\u951a\u4f4d\u7f6e");
    model.param().set("l_e_s", "120[um]", "\u77ed\u7535\u6781\u957f\u5ea6");
    model.param().set("l_e_l", "140[um]", "\u957f\u7535\u6781\u957f\u5ea6");
    model.param().set("l_p", "16[um]", "\u6781\u677f\u957f\u5ea6");
    model.param().set("w_p", "8[um]", "\u6781\u677f\u5bbd\u5ea6");
    model.param().set("r_an", "3[um]", "\u7535\u6781\u951a\u534a\u5f84");
    model.param().set("l_ovrlp", "104[um]", "\u6307\u91cd\u53e0\u957f\u5ea6");
    model.param().set("l_spAssm", "l_anch_base+2*(w_f+w_sp)+3*g_sp", "\u5f39\u7c27\u88c5\u914d\u957f\u5ea6");
    model.param().set("l_polySi", "l_PM+2*l_spAssm", "\u603b\u957f\u5ea6");
    model.param().set("hw_polySi", "w_PM/2+l_f+l_p+l_e_l-l_ovrlp", "\u603b\u534a\u5bbd");

    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u68c0\u6d4b\u8d28\u91cf\u5757");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_PM", "l_PM");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_PM", "w_PM");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "t_PM", "tSi");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_f", "l_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_f", "w_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_st", "n_st");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_f", "n_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "g_f", "g_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "g_st", "g_st");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "x_st", "x_st");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "x_f", "x_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_eh", "w_eh");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "p_eh", "p_eh");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u5f39\u7c27 1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "l_sp", "l_sp");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "w_sp", "w_sp");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "g_sp", "g_sp");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "w_sp_conn", "w_sp_conn");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "w_f", "w_f");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "l_anch_base", "l_anch_base");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "w_anch_base", "w_anch_base");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "r_anch", "r_anch");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "x_anch", "x_anch");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "t_sp", "tSi");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "t_anch", "tOx");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "x_sp", "l_PM");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi3", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi3").label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u5f39\u7c27 2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "mirror", 0);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "l_sp", "l_sp+10[um]");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "w_anch_base", "w_anch_base+10[um]");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "x_sp", "0[um]");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "LH", 0);
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "l_e", "l_e_l");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "w_e", "w_f");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "l_p", "l_p");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "w_p", "w_p");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "r_an", "r_an");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "t_e", "tSi");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "t_an", "tOx");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "n_e", "n_f+1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "p_e", "3*(w_f+g_f)");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "x_e", "x_f-w_f-g_f");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "y_e", "w_PM/2+l_f-l_ovrlp");
    model.component("comp1").geom("geom1").feature("pi4")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u611f\u5e94\u7535\u6781 L");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature().duplicate("pi5", "pi4");
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u611f\u5e94\u7535\u6781 R");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "LH", 1);
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "l_e", "l_e_s");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "x_e", "x_f-2*(w_f+g_f)");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi6", "pi5");
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi6")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u81ea\u68c0\u7535\u6781 L 1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "LH", 0);
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "n_e", "n_st");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "p_e", "3*w_f+2*g_f+g_st");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "x_e", "x_st-w_f-g_f");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi7", "pi6");
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi7")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u81ea\u68c0\u7535\u6781 L 2");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "l_e", "l_e_l");
    model.component("comp1").geom("geom1").feature("pi7")
         .setEntry("inputexpr", "x_e", "l_PM-(x_st+w_f+g_f)-(n_st-1)*(3*w_f+2*g_f+g_st)-w_f");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi8", "pi7");
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi8")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u81ea\u68c0\u7535\u6781 R 1");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "LH", 1);
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "x_e", "x_st-w_f-g_f+2*(w_f+g_f)");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi9", "pi8");
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi9")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u81ea\u68c0\u7535\u6781 R 2");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("inputexpr", "l_e", "l_e_s");
    model.component("comp1").geom("geom1").feature("pi9")
         .setEntry("inputexpr", "x_e", "l_PM-(x_st+w_f+g_f)-(n_st-1)*(3*w_f+2*g_f+g_st)-w_f+2*(w_f+g_f)");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u6c14\u7bb1");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"l_polySi+40[um]", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "hw_polySi+20[um]", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "10[um]", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-l_spAssm-20[um]", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "-tOx", 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "tOx", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "tSi", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u63a5\u5730\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-tOx");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"l_PM", "w_PM/2+l_f"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "tOx", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u591a\u6676\u7845");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u68c0\u6d4b\u8d28\u91cf\u5757\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel1.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_csel1.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel1.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetodom", "pi4_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u611f\u5e94\u7535\u6781\u5de6\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetobnd", "pi4_csel1.bnd", "csel3");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetodom", "pi5_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u611f\u5e94\u7535\u6781\u53f3\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetobnd", "pi5_csel1.bnd", "csel4");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u81ea\u68c0\u7535\u6781\u5de6\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("selcontributetobnd", "pi6_csel1.bnd", "csel5");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetodom", "pi7_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetobnd", "pi7_csel1.bnd", "csel5");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selcontributetodom", "pi8_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("\u81ea\u68c0\u7535\u6781\u53f3\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selcontributetobnd", "pi8_csel1.bnd", "csel6");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("selcontributetodom", "pi9_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("selcontributetobnd", "pi9_csel1.bnd", "csel6");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("selcontributetodom", "pi6_csel1.dom", "csel1");

    model.component("comp1").selection().create("box1", "Box");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("ymin", -0.1);
    model.component("comp1").selection("box1").set("ymax", 0.1);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection("box1").label("\u5bf9\u79f0\u5e73\u9762");
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u951a\u5e73\u9762");
    model.component("comp1").selection("box2").set("entitydim", 2);
    model.component("comp1").selection("box2").set("zmin", "-tOx*1.01");
    model.component("comp1").selection("box2").set("zmax", "-tOx*0.99");
    model.component("comp1").selection("box2").set("condition", "inside");
    model.component("comp1").selection().create("box3", "Box");
    model.component("comp1").selection("box3").label("\u7f51\u683c\u5212\u5206\u5e73\u9762");
    model.component("comp1").selection("box3").set("entitydim", 2);
    model.component("comp1").selection("box3").set("zmin", "-tOx*0.01");
    model.component("comp1").selection("box3").set("zmax", "tOx*0.01");
    model.component("comp1").selection("box3").set("condition", "inside");

    model.title("\u8868\u9762\u5fae\u673a\u68b0\u52a0\u901f\u5ea6\u8ba1\u51e0\u4f55");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u5305\u542b\u201c\u8868\u9762\u5fae\u673a\u68b0\u52a0\u901f\u5ea6\u8ba1\u201d\u6a21\u578b\u7684\u7269\u7406\u573a\u63a5\u53e3\u548c\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("surface_micromachined_accelerometer_geom.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
