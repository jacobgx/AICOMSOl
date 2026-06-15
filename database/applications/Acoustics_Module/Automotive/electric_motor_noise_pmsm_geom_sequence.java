/*
 * electric_motor_noise_pmsm_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class electric_motor_noise_pmsm_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_stator", "57[mm]", "\u5b9a\u5b50\u5916\u534a\u5f84");
    model.param().set("n_sectors", "3", "\u6247\u533a\u6570");
    model.param().set("h_stat", "25[mm]", "\u5b9a\u5b50\u603b\u9ad8\u5ea6");
    model.param().set("air_gap", "1[mm]", "\u8f6c\u5b50\u548c\u5b9a\u5b50\u4e4b\u95f4\u7684\u95f4\u9699");
    model.param().set("th_magnet", "10[mm]", "\u78c1\u4f53\u539a\u5ea6");
    model.param().set("angle_teeth_gap", "2[deg]", "\u9f7f\u9699\u89d2\u5ea6");
    model.param().set("angle_coil", "20[deg]", "\u7ebf\u5708\u89d2\u5ea6");
    model.param().set("angle_magnet", "3[deg]", "\u78c1\u4f53\u89d2\u5ea6");
    model.param().set("th_in_stator", "3[mm]", "\u5b9a\u5b50\u5185\u90e8\u539a\u5ea6");
    model.param().set("h_coil_angle", "5[mm]", "\u5b9a\u5b50\u5185\u90e8\u539a\u5ea6");
    model.param().set("th_out_stator", "5[mm]", "\u5b9a\u5b50\u5916\u90e8\u539a\u5ea6");
    model.param().set("fillet", "1[mm]", "\u5706\u89d2\u534a\u5f84");
    model.param().set("r_shaft", "10.5[mm]", "\u8f74\u534a\u5f84");
    model.param().set("n_poles", "6", "\u78c1\u6781\u6570");
    model.param().set("R_rotor", "r_stator-h_stat-air_gap", "\u8f6c\u5b50\u534a\u5f84");
    model.param().set("R_instator", "R_rotor+air_gap", "\u5b9a\u5b50\u5185\u534a\u5f84");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "electric_motor_noise_pmsm_geom_sequence.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").selection("offsetvertex").set("imp1", 372);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r_stator");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("layer", "th_out_stator", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .setIndex("layer", "h_stat-th_out_stator", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("layer", "air_gap/2", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-h_stat+th_in_stator)", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-h_stat+th_in_stator+h_coil_angle)*cos(-angle_coil/2)", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-h_stat+th_in_stator+h_coil_angle)*sin(-angle_coil/2)", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-th_out_stator)*cos(-angle_coil/2)", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-th_out_stator)*sin(-angle_coil/2)", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-th_out_stator/2)", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-th_out_stator)*cos(angle_coil/2)", 4, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-th_out_stator)*sin(angle_coil/2)", 4, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-h_stat+th_in_stator+h_coil_angle)*cos(angle_coil/2)", 5, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "(r_stator-h_stat+th_in_stator+h_coil_angle)*sin(angle_coil/2)", 5, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(r_stator-h_stat)*cos(-angle_teeth_gap/2)", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(r_stator-h_stat)*sin(-angle_teeth_gap/2)", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(r_stator-th_out_stator)*cos(-angle_teeth_gap/2)", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(r_stator-th_out_stator)*sin(-angle_teeth_gap/2)", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(r_stator-th_out_stator)*cos(+angle_teeth_gap/2)", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(r_stator-th_out_stator)*sin(+angle_teeth_gap/2)", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(r_stator-h_stat)*cos(+angle_teeth_gap/2)", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "(r_stator-h_stat)*sin(+angle_teeth_gap/2)", 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("pol1", "pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("c1", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("uni1", 1, 4, 5, 12, 13, 14);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").set("del1(2)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1")
         .set("rot", "range(360/(3*n_sectors),360/(3*n_sectors),360)");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").selection("input")
         .set("del1(1)", "rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del2").selection("input")
         .set("uni2", 75, 76, 83, 84, 91, 92, 99, 100, 101, 102, 113, 114, 121, 122, 123, 124, 127, 128, 135, 136);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del3", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del3").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del3").selection("input").set("del2", 21);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("del3", 22, 23, 24, 25, 38, 39, 40, 41, 54, 55, 58, 59, 70, 71, 72, 73, 81, 82);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext1").selection("vertex").set("imp1", 490);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "imp1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").selection("offsetvertex").set("uni1", 422);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "r_stator-h_stat-air_gap/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").setIndex("layer", "air_gap/2", 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").setIndex("layer", "th_magnet", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .setIndex("layer", "r_stator-h_stat-air_gap-th_magnet-r_shaft", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1")
         .set("coord1", new String[]{"(r_stator-h_stat-air_gap-th_magnet)*cos(-angle_magnet/2)", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1")
         .setIndex("coord1", "(r_stator-h_stat-air_gap-th_magnet)*sin(-angle_magnet/2)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1")
         .set("coord2", new String[]{"(r_stator-h_stat-air_gap)*cos(-angle_magnet/2)", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1")
         .setIndex("coord2", "(r_stator-h_stat-air_gap)*sin(-angle_magnet/2)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot1").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot1")
         .set("rot", "range(360/(n_poles),360/(n_poles),360) range(360/(n_poles)+angle_magnet,360/(n_poles),360+angle_magnet)");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("uni1").selection("input")
         .set("c1", "rot1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("del1").selection("input")
         .set("uni1", 1, 2, 5, 10, 11, 12, 13, 14, 15, 18, 23, 24, 27, 28, 35, 36, 57, 58, 63, 64);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("point")
         .set("del1", 2, 3, 7, 8, 9, 10, 29, 30, 31, 32, 36, 37);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").set("radius", "fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext2").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext2").selection("vertex").set("uni1", 656);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u94c1");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 13, 24, 49);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u7ebf\u5708");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .set("fin", 25, 26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u94dd");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .set("fin", 14, 15, 16, 17, 19, 20, 21, 22, 45, 46, 47, 50, 51, 52);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u7ed3\u6784");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel1", "sel2", "sel3"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u5185\u8154");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .set("fin", 18, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 48, 59, 60, 61, 62, 63, 64, 65, 66, 67);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("PML");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .set("fin", 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 53, 54, 55, 56, 57, 58);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u7a7a\u6c14");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"unisel1", "sel4"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u8d1f\u8f7d\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection")
         .set("fin", 335, 336, 338, 339, 347, 349, 352, 355, 359, 360, 366, 367, 371, 372, 374, 375, 377, 378, 380, 381, 382, 383, 384, 385, 389, 390, 391, 392, 393, 394, 396, 398, 401, 402, 404, 407, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 425, 426, 427, 428, 438, 439, 440, 441, 444, 446, 447, 448, 450, 451, 456, 457, 460, 461, 462, 463, 466, 469, 470, 471, 472, 473, 474, 475, 476, 477, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 497, 498, 500, 501, 506, 509, 514, 515, 516, 517, 520, 521, 523, 524, 526, 529, 532, 533, 534, 535, 536, 537, 553, 554, 555, 556);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u5916\u573a\u8ba1\u7b97");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("fin", 31, 32, 36, 39, 924);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection")
         .set("fin", 213, 214, 217, 247, 249, 257, 291, 292, 294, 295, 296, 297, 299, 300, 581, 582, 597, 690, 692, 719, 725, 726, 728, 729, 730, 731, 733, 734);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("\u5df2\u5212\u5206\u7f51\u683c\u7684\u622a\u9762");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection")
         .set("fin", 301, 306, 317, 331, 334, 337, 351, 354, 376, 379, 403, 406, 431, 437, 445, 464, 467, 496, 499, 505, 508, 525, 528, 538, 949, 952, 957, 966, 969, 976, 993, 996);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").create("sel10", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel10").label("\u626b\u63a0\u57df");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection")
         .set("fin", 13, 21, 22, 23, 24, 25, 26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 49, 60, 61, 62, 63, 64, 65, 66, 67);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").create("comsel2", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel2").label("\u56db\u9762\u4f53\u57df");
    model.component("comp1").geom("geom1").feature("comsel2").set("input", new String[]{"sel4", "sel5", "sel10"});

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("renderwireframe", false);
    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(3);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 10);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 4);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 9);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 3);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 6);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 11);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 5);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 12);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 7);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 2);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 1);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 53);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 8);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 54);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 56);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 55);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 58);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 57);
    model.component("comp1").view("view1").hideObjects().clear();
    model.component("comp1").view("view1").set("renderwireframe", true);
    model.component("comp1").view("view1").set("showgrid", true);

    model.title(null);

    model.description("");

    model.label("electric_motor_noise_pmsm_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
