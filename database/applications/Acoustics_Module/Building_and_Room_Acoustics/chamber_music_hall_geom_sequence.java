/*
 * chamber_music_hall_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class chamber_music_hall_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "chamber_music_hall.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("imp1", 238);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.8, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface")
         .set("ext1", 408, 419, 197, 213, 661);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.8, 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("ext2", 724);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.8, 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").selection("inputface").set("ext3", 746);
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", 0.8, 0);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set("ext4", 199, 220, 228, 240, 252, 282, 300, 341, 359, 436, 452, 460, 481, 519, 543, 582, 597, 712, 714, 721, 723, 725, 742, 743, 744, 745, 746, 750, 751, 754, 755, 760);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u6240\u6709\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u821e\u53f0\u677f");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .set("del1", 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u7a97\u6237");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .set("del1", 194, 369, 540, 725, 726, 727, 728);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u5ea7\u4f4d");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .set("del1", 158, 160, 162, 164, 166, 167, 168, 169, 195, 197, 199, 200, 205, 209, 211, 222, 223, 224, 229, 231, 232, 246, 247, 248, 249, 257, 263, 273, 274, 275, 280, 282, 283, 284, 285, 286, 287, 288, 289, 309, 315, 319, 321, 330, 331, 332, 337, 339, 340, 341, 342, 343, 344, 345, 346, 370, 376, 379, 381, 388, 411, 412, 414, 425, 427, 428, 445, 446, 447, 452, 454, 455, 459, 460, 461, 462, 463, 464, 477, 482, 488, 492, 494, 502, 503, 504, 509, 511, 512, 516, 517, 521, 522, 523, 524, 541, 547, 551, 553, 560, 561, 565, 570, 572, 573, 591, 597, 607, 608, 610, 613, 616, 618, 619, 620, 621, 625, 626, 627, 628, 635, 636, 637, 638, 640, 641, 642, 643, 681, 695, 696, 697, 698, 703, 704, 705, 707, 714, 715, 717, 719, 720, 721, 722, 723);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u5730\u677f");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .set("del1", 12, 96, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 109, 110, 111, 112, 113, 114, 115, 117, 119, 122, 126, 132, 135, 136, 137, 138, 139, 140, 142, 146, 150, 154, 155, 184, 185, 188, 190, 193, 298, 300, 304, 363, 365, 368, 390, 393, 396, 397, 408, 413, 473, 475, 476, 534, 536, 539, 612, 617, 622, 624, 631, 632, 639, 659, 677, 684, 685, 699, 700, 701, 702, 706, 712, 713, 716, 718, 730);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u5929\u82b1\u677f");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .set("del1", 2, 8, 97, 120, 127, 143, 152, 181, 186, 191, 238, 241, 250, 252, 295, 301, 305, 307, 352, 355, 359, 361, 366, 400, 403, 407, 415, 417, 456, 470, 478, 480, 513, 518, 530, 532, 537, 562, 574, 584, 586, 646, 647, 649, 650, 652, 653, 655, 656, 663, 690, 693, 709);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u7ed3\u6784\u5316\u7070\u6ce5");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection")
         .set("del1", 1, 3, 4, 5, 6, 7, 9, 108, 121, 128, 129, 130, 131, 145, 148, 149, 151, 153, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 182, 187, 198, 201, 202, 203, 204, 206, 208, 212, 213, 214, 215, 216, 218, 220, 225, 227, 233, 235, 239, 243, 251, 253, 259, 260, 261, 262, 264, 266, 267, 268, 269, 270, 271, 272, 276, 278, 290, 292, 296, 303, 306, 308, 311, 312, 313, 314, 316, 318, 323, 324, 326, 327, 328, 329, 333, 335, 347, 349, 353, 357, 358, 360, 362, 372, 373, 374, 375, 377, 378, 382, 383, 384, 385, 386, 387, 389, 391, 392, 394, 395, 399, 401, 405, 416, 418, 426, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 441, 443, 448, 450, 457, 465, 467, 472, 479, 481, 484, 485, 486, 487, 489, 491, 496, 497, 498, 499, 500, 501, 505, 507, 514, 520, 525, 527, 531, 533, 543, 544, 545, 546, 548, 550, 554, 555, 556, 557, 558, 559, 563, 566, 568, 576, 577, 579, 585, 587, 593, 594, 595, 596, 598, 600, 601, 602, 603, 604, 605, 606, 611, 614, 615, 629, 630, 634, 680, 711);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u7070\u6ce5");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("subtract", new String[]{"sel1", "sel2", "sel3", "sel4", "sel5", "sel6"});
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("chamber_music_hall_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
