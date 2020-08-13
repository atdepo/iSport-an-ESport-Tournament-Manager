// Select and cache elements to work with
var el = {
  sorter: $('.js-sort-container')
};

// classnames to apply for visual changes
var classes = {
  sorter: {
    hot: 'sorter--hot',
    new: 'sorter--new'
  }
};

// Defaults / Initial values
var isHot = true;

// Listeners
el.sorter.click(function() {
  isHot = !isHot;
  // Update classnames accordingly
  el.sorter.addClass(isHot ? classes.sorter.hot : classes.sorter.new);
  el.sorter.removeClass(isHot ? classes.sorter.new : classes.sorter.hot);
});
